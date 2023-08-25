package com.contest.xczx_plus_content_api;

import com.contest.xczx_plus_content_model.content.Pto.ReturnReviseCourseDto;
import com.contest.xczx_plus_content_service.content.feign.feignclient2;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
class ApplicationsTests {

    @Autowired
    feignclient2 feignclient2;
    @Autowired
    CourseBaseInfoService CourseBaseInfoService;

    @Test
    void test() throws IOException {
        ReturnReviseCourseDto dto = CourseBaseInfoService.selectCourseBasebyId(83L);
        System.out.println(dto.getChangeDate());
        System.out.println(dto.getCreateDate());
        System.out.println(LocalDateTime.now());
        Boolean add = feignclient2.add(dto);
        System.out.println(add);
    }
}
