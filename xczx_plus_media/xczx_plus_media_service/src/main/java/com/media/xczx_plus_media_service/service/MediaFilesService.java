package com.media.xczx_plus_media_service.service;

import com.media.xczx_plus_media_model.model.Dao.MediaFiles;
import com.media.xczx_plus_media_model.model.Dto.ReturnMediaFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaFilesService {

    //根据id获取信息
    MediaFiles SelectFile(String Md5id);
    //删除文件
    void DeleteFile(String Md5id);
    //插入图片文件
    ReturnMediaFileDto FileAdd(Long companyId,MultipartFile filedata,String objectName) throws IOException;
    //查看文件是否存在
    Boolean checkFile(String fileMd5);
    //查看文件块是否存在
    Boolean checkFile(String fileMd5, int chunk);
    //合并文件块
    Boolean MergeFiles(Long companyId,String fileMd5,String fileName,int chunkTotal);
    //删除文件块
    void DeleteFiles(String fileMd5, int chunkTotal);
}
