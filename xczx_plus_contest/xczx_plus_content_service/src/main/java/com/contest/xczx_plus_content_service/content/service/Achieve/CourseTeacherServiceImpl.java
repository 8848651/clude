package com.contest.xczx_plus_content_service.content.service.Achieve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.contest.xczx_plus_content_model.content.Dao.CourseTeacher;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseTeacherService;
import com.contest.xczx_plus_content_service.content.mapper.CourseTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;


    /**
     * 增加教师
     */
    @Override
    public CourseTeacher AddCourseTeacher(CourseTeacher CT, Long companyId){
        if(CT.getId()==null) {
            courseTeacherMapper.insert(CT);
            return CT;
        }
        courseTeacherMapper.updateById(CT);
        return CT;
    }

    /**
     * 删除教师
     */
    @Override
    public void DelectCourseTeacher(Long teachid){
        courseTeacherMapper.deleteById(teachid);
    }


    /**
     * 删除教师
     */
    @Override
    public void DelectCourseTeacherPlus(Long courseid){
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseid);
        courseTeacherMapper.delete(queryWrapper);
    }

    /**
     * 更新教师
     */
    @Override
    public CourseTeacher ReviseCourseTeacher(CourseTeacher CT,Long companyId){
        courseTeacherMapper.updateById(CT);
        return CT;
    }

    /**
     * 查询教师
     */
    @Override
    public List<CourseTeacher> SelectCourseTeacher(Long courseid){
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseid);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(queryWrapper);
        return courseTeachers;

    }


}
