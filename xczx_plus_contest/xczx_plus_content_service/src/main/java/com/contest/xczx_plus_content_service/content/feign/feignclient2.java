package com.contest.xczx_plus_content_service.content.feign;
import com.contest.xczx_plus_content_model.content.Pto.ReturnAddCourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@FeignClient(value = "search",fallback = Fallback.class)
public interface feignclient2 {

    @PostMapping("/search/index/course")
    Boolean add(@RequestBody ReturnAddCourseDto courseIndex) throws IOException;

}
