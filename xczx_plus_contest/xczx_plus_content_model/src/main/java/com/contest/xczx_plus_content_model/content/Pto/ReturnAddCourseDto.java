package com.contest.xczx_plus_content_model.content.Pto;
import com.contest.xczx_plus_content_model.content.Dao.CourseBase;
import lombok.Data;
@Data
//添加课程返回类，基本表+营销表
public class ReturnAddCourseDto extends CourseBase {
 private String charge;
 private Float price;
 private Float originalPrice;
 private String qq;
 private String wechat;
 private String phone;
 private Integer validDays;
 private String mtName;
 private String stName;
}
