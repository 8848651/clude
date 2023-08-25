package com.search.xczx_plus_search.search.Dao;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class CourseIndex implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long companyId;
    private String companyName;
    private String name;
    private String users;
    private String tags;
    private String mt;
    private String mtName;
    private String st;
    private String stName;
    private String grade;
    private String teachmode;
    private String pic;
    private String description;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private String status;
    private String remark;
    private String charge;
    private Float price;
    private Float originalPrice;
    private Integer validDays;


}
