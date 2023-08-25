package com.contest.xczx_plus_content_model.content.Pto;

import com.base.xczx_plus_base.base.exception.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
//添加课程接收类
public class ReceiveAddCourseDto {
 private Long id;
 @NotEmpty(message = "新增课程名称不能为空",groups={ValidationGroups.Inster.class})
 @NotEmpty(message = "修改课程名称不能为空",groups={ValidationGroups.Update.class})
 private String name;
 @NotEmpty(message = "适用人群不能为空")
 @Size(message = "适用人群内容过少", min = 10)
 private String users;
 private String tags;
 @NotEmpty(message = "课程分类不能为空")
 private String mt;
 @NotEmpty(message = "课程分类不能为空")
 private String st;
 @NotEmpty(message = "课程等级不能为空")
 private String grade;
 private String teachmode;
 private String description;
 private String pic;
 @NotEmpty(message = "收费规则不能为空")
 private String charge;
 private Float price;
 private Float originalPrice;
 private String qq;
 private String wechat;
 private String phone;
 private Integer validDays;
}