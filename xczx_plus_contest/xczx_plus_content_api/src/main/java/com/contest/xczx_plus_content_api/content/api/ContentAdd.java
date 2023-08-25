package com.contest.xczx_plus_content_api.content.api;
import com.contest.xczx_plus_content_api.content.config.SecurityUtil;
import com.contest.xczx_plus_content_model.content.Pto.AddTeachplanMediaDto;
import com.contest.xczx_plus_content_model.content.Pto.ReceiveAddCourseDto;
import com.contest.xczx_plus_content_model.content.Pto.ReturnAddCourseDto;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseTeacherService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanMediaService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import com.base.xczx_plus_base.base.exception.ValidationGroups;
import com.contest.xczx_plus_content_model.content.Dao.CourseTeacher;
import com.contest.xczx_plus_content_model.content.Dao.Teachplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ContentAdd {
    @Autowired
    CourseBaseInfoService CInfoService;
    @Autowired
    TeachplanService TlanService;
    @Autowired
    CourseTeacherService courseTeacherService;
    @Autowired
    TeachplanMediaService teachplanMediaService;

    /**
     *添加课程信息
     */
    @PostMapping("/course")
    public ReturnAddCourseDto Add1(@RequestBody @Validated(ValidationGroups.Inster.class) ReceiveAddCourseDto RACD){
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        ReturnAddCourseDto courseBase = CInfoService.addCourseBase(companyId, RACD);
        return courseBase;
    }

    /**
     * 增加课程大纲目录
     * 修改课程大纲目录
     */
    @PostMapping("/teachplan")
    public void Add2(@RequestBody Teachplan RTD) {
        TlanService.AddTeachplan(RTD);
    }

    /**
     * 增加教师
     * 修改教师
     */
    @PostMapping("/courseTeacher")
    public CourseTeacher Add3(@RequestBody CourseTeacher RTD) {
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        return courseTeacherService.AddCourseTeacher(RTD,companyId);
    }

    /**
     * 为课程目录添加媒资
     */
    @PostMapping("/teachplan/association/media")
    public void Add4(@RequestBody AddTeachplanMediaDto addTeachplanMediaDto){
        teachplanMediaService.AddMedia(addTeachplanMediaDto);
    }

}

