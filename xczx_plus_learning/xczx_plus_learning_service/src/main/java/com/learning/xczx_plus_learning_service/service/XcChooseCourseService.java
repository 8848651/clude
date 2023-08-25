package com.learning.xczx_plus_learning_service.service;

import com.learning.xczx_plus_learning_model.Dto.XcChooseCourseDto;
import com.learning.xczx_plus_learning_model.Dto.XcCourseTablesDto;

public interface XcChooseCourseService {

    /**
     * 将课程添加到选课表中，如果是免费直接添加到选课完成表中
     * @param userid
     * @param courseid
     * @return
     */
    XcChooseCourseDto AddCourseService(String userid, Long courseid);

    /**
     * 查询课程有没有学习资格
     * @param userid
     * @param courseid
     * @return
     */
    XcCourseTablesDto SelectCourseService(String userid, Long courseid);
}
