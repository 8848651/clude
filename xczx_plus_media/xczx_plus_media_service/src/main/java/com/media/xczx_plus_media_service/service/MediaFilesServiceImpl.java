package com.media.xczx_plus_media_service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.xczx_plus_base.base.model.PageParams;
import com.base.xczx_plus_base.base.model.PageResult;
import com.media.xczx_plus_media_model.model.Dao.MediaFiles;
import com.media.xczx_plus_media_model.model.Dao.MediaProcess;
import com.media.xczx_plus_media_model.model.Dto.ReturnMediaFileDto;
import com.media.xczx_plus_media_service.mapper.MediaFilesMapper;
import com.media.xczx_plus_media_service.mapper.MediaProcessMapper;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MediaFilesServiceImpl implements MediaFilesService {
    private static int nameber =1024*1024*10;

    @Autowired
    private MinionService minionService;

    @Autowired
    private MediaFilesMapper mediaFilesMapper;

    @Autowired
    private MediaProcessMapper mediaProcessMapper;

    @Value("${minio.bucket.video}")
    private String video;

    @Value("${minio.bucket.files}")
    private String file;

    public PageResult<MediaFiles>  SelectPage(Long companyId, PageParams pageParams,String fileType,String filename){
        LambdaQueryWrapper<MediaFiles> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(filename), MediaFiles::getFilename,filename);
        queryWrapper.eq(StringUtils.isNotEmpty(fileType),MediaFiles::getFileType, fileType);
        queryWrapper.eq(MediaFiles::getCompanyId, companyId);
        Page<MediaFiles> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<MediaFiles> pageResult = mediaFilesMapper.selectPage(page, queryWrapper);
        List<MediaFiles> list = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageResult<MediaFiles> courseBasePageResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;
    }

   @Override
   public MediaFiles SelectFile(String Md5id){
       MediaFiles mediaFiles = mediaFilesMapper.selectById(Md5id);
       return mediaFiles;
   }

    @Override
    public void DeleteFile(String Md5id){

    }
//---------------------------------------------------------------------------
    private String getFileMd5(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String fileMd5 = DigestUtils.md5Hex(fileInputStream);
            return fileMd5;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getFolderPath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String folder = sdf.format(new Date()).replace("-", "/")+"/";
        return folder;
    }

    @Override
    public ReturnMediaFileDto FileAdd(Long companyId, MultipartFile filedata,String objectName) throws IOException {
        String contentType = filedata.getContentType();
        String filename = filedata.getName().split("\\.")[0];
        File tempFile = File.createTempFile("minio", ".temp");
        filedata.transferTo(tempFile);
        String fileMd5 = getFileMd5(tempFile);
        String folderPath = getFolderPath();
        String[] split = contentType.split("/");
        String filetype="001003";
        String tages="其他";
        if(split[0].equals("image")){
            filetype="001001";
            tages="课程图片";
        }
        String filepath=null;
        if(objectName==null){
            filepath = folderPath + fileMd5+"."+split[split.length-1];
        }else{
            filepath =objectName;
            contentType= "text/html";
        }
        MediaFiles mediaFiles= mediaFilesMapper.selectById(fileMd5);
        if(mediaFiles==null){
            mediaFiles = new MediaFiles();
            mediaFiles.setId(fileMd5);
            mediaFiles.setTags(tages);
            mediaFiles.setFileType(filetype);
            mediaFiles.setCompanyId(companyId);
            mediaFiles.setFilename(filename);
            mediaFiles.setFileSize(filedata.getSize());
            mediaFiles.setBucket(file);
            mediaFiles.setFilePath(filepath);
            mediaFiles.setFileId(fileMd5);
            mediaFiles.setUrl("/"+file+"/"+filepath);
            mediaFiles.setCreateDate(LocalDateTime.now());
            mediaFiles.setStatus("1");
            mediaFiles.setAuditStatus("002003");
            mediaFilesMapper.insert(mediaFiles);
            minionService.MinionAdd(file,filepath,tempFile.getAbsolutePath(),contentType);
            tempFile.delete();
        }
        return new ReturnMediaFileDto(mediaFiles);
    }

//-----------------------------------------------------------------------------

    /**
     * 检查某文件是否存在
     */
    @Override
    public Boolean checkFile(String fileMd5) {
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
        if(mediaFiles!=null){
            String bucket = mediaFiles.getBucket();
            String filePath = mediaFiles.getFilePath();
            if(minionService.MinioExist(bucket,filePath)){
                return true;
            }else{
                mediaFilesMapper.deleteById(fileMd5);
            }
        }
        return false;
    }

    /**
     * 检查某文件块是否存在
     */
    @Override
    public Boolean checkFile(String fileMd5, int chunk){
        return minionService.MinioExist(video,fileMd5 +"/"+ chunk);
    }

    /**
     * 添加的文件，总文件的Md5值，第i块
     */
    public Boolean VideoAdd(MultipartFile file,String fileMd5,int i) throws IOException {
        File tempFile = File.createTempFile("minio", ".temp");
        file.transferTo(tempFile);
        String minioName = fileMd5+ "/" +i;
        Boolean aBoolean = minionService.MinionAdd(video, minioName, tempFile.getAbsolutePath(), file.getContentType());
        tempFile.delete();
        return aBoolean;
    }

    /**
     * 合并
     */
    @Override
    public Boolean MergeFiles(Long companyId, String fileMd5, String fileName, int chunkTotal) {
        String[] split1 = fileName.split("\\.");
        String Extension = split1[split1.length - 1];
        String path = getFolderPath() + fileMd5 +"."+ Extension;
        boolean b = minionService.MinioMergeblock(video, path, fileMd5, chunkTotal);
        System.out.println(b);
        File file=minionService.MinioDownload(video,path,"minio",".temp");
        String fileMd51 = getFileMd5(file);
        if(!fileMd5.equals(fileMd51)){
            if(minionService.MinioExist(video,path)) {
                minionService.MinionDelete(video, path);
            }
            file.delete();
            return false;
        }
        file.delete();
        Boolean insert = Insert(fileMd5, companyId, path, fileName);
        return insert;
    }

    /**
     * 删除所有块
     */
    @Override
    public void DeleteFiles(String fileMd5, int chunkTotal) {
        minionService.MinioDeleteblock(video,fileMd5,chunkTotal);
    }

    public Boolean Insert(String fileMd5,Long companyId,String path,String fileName){
        String[] split1 = fileName.split("\\.");
        String Extension = split1[split1.length - 1];
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch("."+Extension);
        ContentInfo extensionMatci = ContentInfoUtil.findExtensionMatch(".mp4");
        String mimeType1 = extensionMatch.getMimeType();
        String mimeType2 = extensionMatci.getMimeType();
        String[] split = mimeType1.split("/");
        String filetype="001003";
        String tages="其他";
        if(split[0].equals("video")){
            filetype="001002";
            tages="课程视频";
        }
        //插入media_files
        MediaFiles mediaFiles=new MediaFiles();
        mediaFiles.setId(fileMd5);
        mediaFiles.setCompanyId(companyId);
        mediaFiles.setStatus("1");
        mediaFiles.setBucket(video);
        mediaFiles.setAuditStatus("002003");
        mediaFiles.setCreateDate(LocalDateTime.now());
        mediaFiles.setUrl("/"+video+"/"+path);
        mediaFiles.setFilePath(path);
        mediaFiles.setFileId(fileMd5);
        mediaFiles.setFilename(fileName);
        mediaFiles.setFileType(filetype);
        mediaFiles.setTags(tages);
        if(mimeType1.equals(mimeType2)){
            MediaFiles mediaFiles1 = mediaFilesMapper.selectById(fileMd5);
            if(mediaFiles1==null) {
                mediaFilesMapper.insert(mediaFiles);
            }
            return true;
        }
        //插入任务调度数据库
        MediaProcess mediaProcess=new MediaProcess();
        BeanUtils.copyProperties(mediaFiles,mediaProcess);
        mediaProcess.setStatus("1");
        mediaProcess.setFailCount(0);
        mediaProcess.setType(mimeType1);
        MediaFiles mediaFiles1 = mediaFilesMapper.selectById(fileMd5);
        if(mediaFiles1==null) {
            mediaFilesMapper.insert(mediaFiles);
        }
        mediaProcessMapper.insert(mediaProcess);
        return true;
    }


}
