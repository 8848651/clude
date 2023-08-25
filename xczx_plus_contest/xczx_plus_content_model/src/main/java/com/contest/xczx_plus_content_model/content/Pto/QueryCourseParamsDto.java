package com.contest.xczx_plus_content_model.content.Pto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
//条件查询类
public class QueryCourseParamsDto {
    private String courseName;
    private String auditStatus;
    private String publishStatus;
}

