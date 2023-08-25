package com.learning.xczx_plus_learning_api.Learing;

import com.base.xczx_plus_base.base.exception.XczxPlusException;
import com.base.xczx_plus_base.base.model.PageResult;
import com.base.xczx_plus_base.base.model.RestResponse;
import com.learning.xczx_plus_learning_api.config.SecurityUtil;
import com.learning.xczx_plus_learning_model.Dao.XcCourseTables;
import com.learning.xczx_plus_learning_model.Dto.MyCourseTableParams;
import com.learning.xczx_plus_learning_model.Dto.XcChooseCourseDto;
import com.learning.xczx_plus_learning_model.Dto.XcCourseTablesDto;
import com.learning.xczx_plus_learning_service.feignclient.feignclient2;
import com.learning.xczx_plus_learning_service.service.XcChooseCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CourseSelect {

    @Autowired
    XcChooseCourseServiceImpl XcChooseCourseServiceImpl;
    @Autowired
    feignclient2 feignclient2;

    //添加选课
    @PostMapping("/choosecourse/{courseId}")
    public XcChooseCourseDto addChooseCourse(@PathVariable("courseId") Long courseId) {
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long userid = Long.parseLong(XcUser.getId());
        System.out.println(userid);
        XcChooseCourseDto xcChooseCourseDto = XcChooseCourseServiceImpl.AddCourseService(String.valueOf(userid), courseId);
        return xcChooseCourseDto;
    }

    //查询学习资格
    @PostMapping("/choosecourse/learnstatus/{courseId}")
    public XcCourseTablesDto getLearnstatus(@PathVariable("courseId") Long courseId) {
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        long userid = Long.parseLong(XcUser.getId());
        XcCourseTablesDto xcCourseTablesDto = XcChooseCourseServiceImpl.SelectCourseService(String.valueOf(userid), courseId);
        return xcCourseTablesDto;

    }

    @GetMapping("/mycoursetable")
    public PageResult<XcCourseTables> mycoursetable(MyCourseTableParams params) {
        SecurityUtil.XcUser XcUser=SecurityUtil.getUser();
        if(XcUser==null){
            throw new XczxPlusException("请登录");
        }
        long userid = Long.parseLong(XcUser.getId());
        params.setUserId(String.valueOf(userid));
        PageResult<XcCourseTables> result = XcChooseCourseServiceImpl.SelectCourse(params);
        return result;
    }


    //获取视频url
    @GetMapping("/open/learn/getvideo/{courseId}/{teachplanId}/{mediaId}")
    public RestResponse<String> getvideo(@PathVariable("courseId") Long courseId, @PathVariable("teachplanId") Long teachplanId, @PathVariable("mediaId") String mediaId) {
        String Url = feignclient2.getPlayUrlByMediaId(mediaId);
        return RestResponse.success(Url);
    }
}
