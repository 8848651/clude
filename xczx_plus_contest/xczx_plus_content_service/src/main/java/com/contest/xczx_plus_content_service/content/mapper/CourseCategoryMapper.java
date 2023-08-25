package com.contest.xczx_plus_content_service.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.contest.xczx_plus_content_model.content.Dao.CourseCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    List<CourseCategory> SelectCourseCategory(String id);

    List<CourseCategory> SelectCourseCategorys();
}
