package com.media.xczx_plus_media_api.Controller;

import com.base.xczx_plus_base.base.exception.XczxPlusException;
import com.base.xczx_plus_base.base.model.PageParams;
import com.base.xczx_plus_base.base.model.PageResult;
import com.base.xczx_plus_base.base.model.RestResponse;
import com.media.xczx_plus_media_api.config.SecurityUtil;
import com.media.xczx_plus_media_model.model.Dao.MediaFiles;
import com.media.xczx_plus_media_service.service.MediaFilesServiceImpl;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "222",tags = "222")
@RestController
@CrossOrigin(origins = "*")
public class MediaSelect {

    @Autowired
    MediaFilesServiceImpl mediaFilesService;

    @PostMapping("/files")
    public PageResult<MediaFiles> list(PageParams pageParams,String fileType,String filename){
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        fileType="001002";
        return mediaFilesService.SelectPage(companyId,pageParams,fileType,filename);
    }


    //检查文件是否存在
    @PostMapping("/upload/checkfile")
    public RestResponse<Boolean> checkfile(@RequestParam("fileMd5") String fileMd5) throws Exception {
        Boolean aBoolean = mediaFilesService.checkFile(fileMd5);
        return RestResponse.success(aBoolean);
    }


    //OK
    //检查第i块是否存在
    //true是存在不传，false是不存在传
    @PostMapping("/upload/checkchunk")
    public RestResponse<Boolean> checkchunk(@RequestParam("fileMd5") String fileMd5,
                                          @RequestParam("chunk") int chunk) throws Exception {
        Boolean aBoolean = mediaFilesService.checkFile(fileMd5, chunk);
        return  RestResponse.success(aBoolean);
    }

    //未登陆时视频的url
    @GetMapping("/open/preview/{mediaId}")
    public RestResponse<String> getPlayUrlByMediaId1(@PathVariable("mediaId") String mediaId){
        MediaFiles mediaFiles = mediaFilesService.SelectFile(mediaId);
        if(mediaFiles == null || StringUtils.isEmpty(mediaFiles.getUrl())){
            XczxPlusException.cast("视频还没有转码处理");
        }
        return RestResponse.success(mediaFiles.getUrl());
    }

    //预览时视频的url
    @GetMapping("/r/{mediaId}")
    public String getPlayUrlByMediaId2(@PathVariable("mediaId") String mediaId){
        MediaFiles mediaFiles = mediaFilesService.SelectFile(mediaId);
        if(mediaFiles == null || StringUtils.isEmpty(mediaFiles.getUrl())){
            XczxPlusException.cast("视频还没有转码处理");
        }
        return mediaFiles.getUrl();
    }
}
