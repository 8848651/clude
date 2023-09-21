package com.contest.xczx_plus_content_service.content.feign;

import com.contest.xczx_plus_content_model.content.Pto.ReturnAddCourseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class Fallback implements feignclient1,feignclient2{
    @Override
    public String Upload(MultipartFile filedata, String objectName) throws IOException {
        return null;
    }

    @Override
    public Boolean add(ReturnAddCourseDto courseIndex) throws IOException {
        return null;
    }
}
