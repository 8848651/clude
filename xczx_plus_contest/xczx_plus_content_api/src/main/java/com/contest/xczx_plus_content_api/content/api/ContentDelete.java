package com.contest.xczx_plus_content_api.content.api;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseTeacherService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanMediaService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ContentDelete {
    @Autowired
    CourseBaseInfoService CInfoService;
    @Autowired
    TeachplanService TlanService;
    @Autowired
    CourseTeacherService courseTeacherService;
    @Autowired
    TeachplanMediaService teachplanMediaService;

    /**
     * 根据id删除课程大纲目录
     */
    @DeleteMapping("/teachplan/{id}")
    public void Delete1(@PathVariable("id") Long id){
        TlanService.DeleteTeachplan(id);
    }

    /**
     * 删除教师
     */
    @DeleteMapping("/ourseTeacher/course/{Course_id}/{teacher_id}")
    public void Delete2(@PathVariable("teacher_id") Long teacher_id){
        courseTeacherService.DelectCourseTeacher(teacher_id);
    }

    /**
     * 删除课程
     */
    @DeleteMapping("/course/{Course_id}")
    public void Delete3(@PathVariable("Course_id") Long Course_id){
        CInfoService.deleteCourseBase(Course_id);
    }


    /**
     * 删除目录绑定视频
     */
    @DeleteMapping("/teachplan/association/media/{teachPlanId}/{mediaId}")
    public void Delete4(@PathVariable("teachPlanId") Long teachPlanId){
        teachplanMediaService.DeleteMedia(teachPlanId);
    }
}

