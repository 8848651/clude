package com.contest.xczx_plus_content_api.content.freemaker;

import com.contest.xczx_plus_content_model.content.Dao.TeachplanMedia;
import com.contest.xczx_plus_content_model.content.Pto.FreemarkerModelDto;
import com.contest.xczx_plus_content_model.content.Pto.TreeTeachplanDto;
import com.contest.xczx_plus_content_service.content.service.Interface.FreemarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FreemarkerController {


    @Autowired
    private FreemarkerService freemarkerService;

    //课程预览
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId){
        ModelAndView modelAndView = new ModelAndView();
        FreemarkerModelDto freemarkerselect = freemarkerService.Freemarkerselect(courseId);
        modelAndView.addObject("model",freemarkerselect);
        modelAndView.setViewName("course_template");
        return modelAndView;
    }



}

