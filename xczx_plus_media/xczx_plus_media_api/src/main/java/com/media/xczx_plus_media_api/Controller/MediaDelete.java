package com.media.xczx_plus_media_api.Controller;

import com.media.xczx_plus_media_service.service.MediaFilesServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "222",tags = "222")
@RestController
@CrossOrigin(origins = "*")
public class MediaDelete {

    @Autowired
    MediaFilesServiceImpl mediaFilesService;

    @DeleteMapping("/{Md5}")
    public void Delete1(@PathVariable Long Md5){

    }
}
