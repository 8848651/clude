package com.contest.xczx_plus_content_service.content.service.Interface;

import com.contest.xczx_plus_content_model.content.Dao.CourseTeacher;

import java.util.List;

public interface CourseTeacherService {


    /**
     * 新增一个教师
     */
    CourseTeacher AddCourseTeacher(CourseTeacher CT, Long companyId);

    /**
     * 根据教师id删除教师
     */
    void DelectCourseTeacher(Long teachid);

    /**
     *
     */
    void DelectCourseTeacherPlus(Long courseid);

    /**
     * 更新教师信息
     */
    CourseTeacher ReviseCourseTeacher(CourseTeacher CT,Long companyId);

    /**
     * 根据courseid查询所有该课程的教师
     */
    List<CourseTeacher> SelectCourseTeacher(Long courseid);
}
