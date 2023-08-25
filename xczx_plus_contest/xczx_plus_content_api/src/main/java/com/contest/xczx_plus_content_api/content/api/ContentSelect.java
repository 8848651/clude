package com.contest.xczx_plus_content_api.content.api;

import com.base.xczx_plus_base.base.exception.XczxPlusException;
import com.contest.xczx_plus_content_api.content.config.SecurityUtil;
import com.contest.xczx_plus_content_model.content.Dao.CoursePublish;
import com.contest.xczx_plus_content_model.content.Pto.*;
import com.contest.xczx_plus_content_service.content.service.Interface.*;
import com.base.xczx_plus_base.base.model.PageParams;
import com.base.xczx_plus_base.base.model.PageResult;
import com.contest.xczx_plus_content_model.content.Dao.CourseBase;
import com.contest.xczx_plus_content_model.content.Dao.CourseTeacher;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "",tags = "")
@RestController
@CrossOrigin(origins = "*")
public class ContentSelect {
    @Autowired
    CourseBaseInfoService CInfoService;
    @Autowired
    CourseCategoryService CCServiceImpl;
    @Autowired
    TeachplanService TlanService;
    @Autowired
    CoursePublishService CoursePublishService;
    @Autowired
    CourseTeacherService courseTeacherService;
    @Autowired
    FreemarkerService freemarkerService;

    /**
     *根据页面和状态查询
     */
    @PostMapping("/course/list")
    public PageResult<CourseBase> select1(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParams){
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long companyId= Long.parseLong(XcUser.getCompanyId());
        PageResult<CourseBase> courseBasePageResult = CInfoService.selectCourseBaseList(companyId,pageParams, queryCourseParams);
        return courseBasePageResult;
    }

    /**
     *查询树
     */
    @GetMapping("/course-category/tree-nodes")
    public List<TreeCourseCategoryDto> select2(){
        return CCServiceImpl.queryTreeNode();
    }

    /**
     * 根据id查询课程信息用于修改
     * 课程管理->编辑
     */
    @GetMapping("/course/{id}")
    public ReturnReviseCourseDto select3(@PathVariable("id") Long id){
       return  CInfoService.selectCourseBasebyId(id);
    }

    /**
     * 根据课程id查询课程大纲，查询目录
     */
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TreeTeachplanDto> select4(@PathVariable("courseId") Long courseId){
        return TlanService.Selectbycourseid(courseId);
    }

    /**
     * 根据id查老师
     * 课程大纲->教师设置,添加完教师后
     */
    @GetMapping("/courseTeacher/list/{id}")
    public List<CourseTeacher> select5(@PathVariable Long id){
        return courseTeacherService.SelectCourseTeacher(id);
    }

    //免费课程 无需登陆
    @GetMapping("/open/course/whole/{courseId}")
    public FreemarkerModelDto getPreviewInfo1(@PathVariable("courseId") Long courseId) {
        FreemarkerModelDto freemarkerselect = freemarkerService.Freemarkerselect(courseId);
        ReturnAddCourseDto base = freemarkerselect.getCourseBase();
        /*if(base.getCharge().equals("201001")){
            throw  new XczxPlusException("付费课程,请登录或加入我的课程");
        }*/
        return freemarkerselect;
    }

    //登陆后
    @GetMapping("/course/whole/{courseId}")
    public FreemarkerModelDto getPreviewInfo2(@PathVariable("courseId") Long courseId) {
        FreemarkerModelDto freemarkerselect = freemarkerService.Freemarkerselect(courseId);
        return freemarkerselect;
    }

    //根据课程id查询发布情况
    //内部查询无需权限
    @GetMapping("/r/{courseId}")
    public CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId) {
        CoursePublish coursePublish = CoursePublishService.SelectCoursePublish(courseId);
        System.out.println(coursePublish.getCreateDate());
        return coursePublish;
    }
}

