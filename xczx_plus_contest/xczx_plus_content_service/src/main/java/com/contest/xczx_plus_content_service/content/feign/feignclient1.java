package com.contest.xczx_plus_content_service.content.feign;
import com.contest.xczx_plus_content_service.content.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(value = "media",configuration = {MultipartSupportConfig.class},fallback = Fallback.class)
public interface feignclient1 {

    @RequestMapping(value = "/media/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String Upload(@RequestPart("filedata") MultipartFile filedata,
                  @RequestParam(value = "objectName", required = false) String objectName) throws IOException;
}
