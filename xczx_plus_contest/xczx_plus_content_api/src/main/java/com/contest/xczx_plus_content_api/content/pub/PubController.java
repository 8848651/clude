package com.contest.xczx_plus_content_api.content.pub;
import com.contest.xczx_plus_content_api.content.config.SecurityUtil;
import com.contest.xczx_plus_content_service.content.service.Interface.CoursePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PubController {


    @Autowired
    private CoursePublishService coursePublishService;

    @PostMapping("/courseaudit/commit/{courseId}")
    public void process(@PathVariable("courseId") Long courseId){
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        coursePublishService.CommitAudit(companyId,courseId);
    }

    @PostMapping ("/coursepublish/{courseId}")
    public void publish(@PathVariable("courseId") Long courseId){
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        coursePublishService.CommitPublish(companyId,courseId);
    }



}

