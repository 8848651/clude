package com.learning.xczx_plus_learning_service.feignclient;
import com.base.xczx_plus_base.base.model.RestResponse;
import com.contest.xczx_plus_content_model.content.Dao.CoursePublish;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "content",fallback = Fallback.class)
public interface feignclient1 {

    //获取该课程的发布信息
    @GetMapping("/content/r/{courseId}")
    CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId);
}
