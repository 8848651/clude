package com.contest.xczx_plus_content_service.content.service.Interface;

import com.contest.xczx_plus_content_model.content.Dao.CourseBase;
import com.contest.xczx_plus_content_model.content.Pto.*;
import com.base.xczx_plus_base.base.model.PageParams;
import com.base.xczx_plus_base.base.model.PageResult;

public interface CourseBaseInfoService  {


    //添加课程信息
    //添加两张表两张表一张course_base，一张是course_market
    ReturnAddCourseDto addCourseBase(Long companyId, ReceiveAddCourseDto addCourseDto);

    //修改课程信息
    //修改两张表一张course_base，一张是course_market
    ReturnReviseCourseDto reviseCourseBase(Long companyid , ReceiveReviseCourseDto RRCD);

    //删除课程信息
    //基本表，目录表，教师表
    void deleteCourseBase(Long id);

    //查询课程信息
    //按page查询
    PageResult<CourseBase> selectCourseBaseList(long aLong,PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    //查询课程信息
    ReturnReviseCourseDto selectCourseBasebyId(Long id);
}

