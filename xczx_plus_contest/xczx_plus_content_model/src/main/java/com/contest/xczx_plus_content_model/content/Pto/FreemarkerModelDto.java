package com.contest.xczx_plus_content_model.content.Pto;

import lombok.Data;

import java.util.List;

@Data
public class FreemarkerModelDto {

    //基本表+营销表
    private ReturnAddCourseDto courseBase;

    //目录表
    private List<TreeTeachplanDto> teachplans;
}
