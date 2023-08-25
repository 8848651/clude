package com.contest.xczx_plus_content_model.content.Dao;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teachplan")
public class Teachplan implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pname;
    private Long parentid;
    private Integer grade;
    private String mediaType;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime startTime;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime endTime;
    private String description;
    private String timelength;
    private Integer orderby;
    private Long courseId;
    private Long coursePubId;
    private Integer status;
    private String isPreview;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime changeDate;

    public Teachplan(Teachplan TT){
        this.id = TT.getId();
        this.pname = TT.getPname();
        this.parentid = TT.getParentid();
        this.grade = TT.getGrade();
        this.mediaType = TT.getMediaType();
        this.startTime = TT.getStartTime();
        this.endTime = TT.getEndTime();
        this.description = TT.getDescription();
        this.timelength = TT.getTimelength();
        this.orderby = TT.getOrderby();
        this.courseId = TT.getCourseId();
        this.coursePubId = TT.getCoursePubId();
        this.status = TT.getStatus();
        this.isPreview = TT.getIsPreview();
        this.createDate = TT.getCreateDate();
        this.changeDate = TT.getChangeDate();
    }
}
