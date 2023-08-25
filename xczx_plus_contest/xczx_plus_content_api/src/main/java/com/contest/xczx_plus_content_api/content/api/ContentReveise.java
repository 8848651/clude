package com.contest.xczx_plus_content_api.content.api;

import com.contest.xczx_plus_content_api.content.config.SecurityUtil;
import com.contest.xczx_plus_content_model.content.Pto.ReceiveReviseCourseDto;
import com.contest.xczx_plus_content_model.content.Pto.ReturnReviseCourseDto;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseTeacherService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import com.base.xczx_plus_base.base.exception.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ContentReveise {
    @Autowired
    CourseBaseInfoService CInfoService;

    /**
     *修改课程,基本信息->课程大纲以PUT方式请求
     */
    @PutMapping("/course")
    public ReturnReviseCourseDto Revise1(@RequestBody @Validated(ValidationGroups.Update.class) ReceiveReviseCourseDto RRCD){
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        ReturnReviseCourseDto returnAddCourseDto = CInfoService.reviseCourseBase(companyId, RRCD);
        return returnAddCourseDto;
    }
}

