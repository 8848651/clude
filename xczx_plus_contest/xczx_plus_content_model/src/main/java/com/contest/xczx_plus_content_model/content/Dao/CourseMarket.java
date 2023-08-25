package com.contest.xczx_plus_content_model.content.Dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("course_market")
public class CourseMarket implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String charge;
    private Float price;
    private Float originalPrice;
    private String qq;
    private String wechat;
    private String phone;
    private Integer validDays;
}
