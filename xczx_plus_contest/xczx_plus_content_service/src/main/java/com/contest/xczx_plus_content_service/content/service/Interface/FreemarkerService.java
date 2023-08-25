package com.contest.xczx_plus_content_service.content.service.Interface;


import com.contest.xczx_plus_content_model.content.Pto.FreemarkerModelDto;

public interface FreemarkerService {


    //为动态页面查询数据
    FreemarkerModelDto Freemarkerselect(Long Courseid);
}
