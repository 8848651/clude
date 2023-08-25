package com.learning.xczx_plus_learning_model.Dao;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@TableName("xc_choose_course")
//选课记录表 记录要支付的课程
public class XcChooseCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long courseId;
    private String courseName;
    private String userId;
    private Long companyId;
    //选课类型
    private String orderType;
    //添加时间
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    //课程有效期(天)
    private Integer validDays;
    private Float coursePrice;
    //选课状态
    private String status;
    //开始服务时间
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime validtimeStart;
    //结束服务时间
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime validtimeEnd;
    private String remarks;


}
