package com.contest.xczx_plus_content_model.content.Dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course_category")
public class CourseCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String label;
    private String parentid;
    private Integer isShow;
    private Integer orderby;
    private Integer isLeaf;
}
