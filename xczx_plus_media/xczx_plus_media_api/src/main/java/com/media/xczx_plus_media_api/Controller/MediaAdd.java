package com.media.xczx_plus_media_api.Controller;

import com.base.xczx_plus_base.base.model.RestResponse;
import com.media.xczx_plus_media_api.config.SecurityUtil;
import com.media.xczx_plus_media_model.model.Dto.ReturnMediaFileDto;
import com.media.xczx_plus_media_service.service.MediaFilesServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Api(value = "222",tags = "222")
@RestController
@CrossOrigin(origins = "*")
public class MediaAdd {

    @Autowired
    MediaFilesServiceImpl mediaFilesService;

    //上传文件
    @RequestMapping(value = "/upload/coursefile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ReturnMediaFileDto Upload(@RequestPart("filedata")MultipartFile filedata,
                                     @RequestParam(value= "objectName",required=false) String objectName) throws IOException {
        long companyId=1L;
        ReturnMediaFileDto returnMediaFileDto = mediaFilesService.FileAdd(companyId, filedata,objectName);
        return returnMediaFileDto;
    }

    //上传第i块文件
    //true是存储成功传递下一块，false是传递失败继续传递当前块
    @PostMapping("/upload/uploadchunk")
    public RestResponse<Boolean> uploadchunk(@RequestParam("file") MultipartFile file,
                                             @RequestParam("fileMd5") String fileMd5,
                                             @RequestParam("chunk") int chunk) throws Exception {
        Boolean aBoolean = mediaFilesService.VideoAdd(file, fileMd5, chunk);
        return  RestResponse.success(aBoolean);
    }


    //合并
    //true是合并成功，false是失败
    @PostMapping("/upload/mergechunks")
    public RestResponse<Boolean> mergechunks(@RequestParam("fileMd5") String fileMd5,
                                  @RequestParam("fileName") String fileName,
                                  @RequestParam("chunkTotal") int chunkTotal) throws Exception {
        SecurityUtil.XcUser XcUser= SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        Boolean aBoolean = mediaFilesService.MergeFiles(companyId, fileMd5, fileName, chunkTotal);
        mediaFilesService.DeleteFiles(fileMd5,chunkTotal);
        return RestResponse.success(aBoolean);
    }

}
