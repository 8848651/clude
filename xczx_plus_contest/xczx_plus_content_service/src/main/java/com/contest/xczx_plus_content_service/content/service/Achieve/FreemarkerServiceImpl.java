package com.contest.xczx_plus_content_service.content.service.Achieve;

import com.contest.xczx_plus_content_model.content.Pto.FreemarkerModelDto;
import com.contest.xczx_plus_content_model.content.Pto.ReturnReviseCourseDto;
import com.contest.xczx_plus_content_model.content.Pto.TreeTeachplanDto;
import com.contest.xczx_plus_content_service.content.service.Interface.CourseBaseInfoService;
import com.contest.xczx_plus_content_service.content.service.Interface.FreemarkerService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanMediaService;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreemarkerServiceImpl implements FreemarkerService {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @Autowired
    private TeachplanService teachplanService;


    @Override
    public FreemarkerModelDto Freemarkerselect(Long Courseid){
        FreemarkerModelDto freemarkerModelDto=new FreemarkerModelDto();
        //添加基本表和营销表
        ReturnReviseCourseDto returnReviseCourseDto = courseBaseInfoService.selectCourseBasebyId(Courseid);
        freemarkerModelDto.setCourseBase(returnReviseCourseDto);
        //添加目录
        List<TreeTeachplanDto> treeTeachplanDtos = teachplanService.Selectbycourseid(Courseid);
        freemarkerModelDto.setTeachplans(treeTeachplanDtos);
        //添加教师

        return freemarkerModelDto;
    }
}
