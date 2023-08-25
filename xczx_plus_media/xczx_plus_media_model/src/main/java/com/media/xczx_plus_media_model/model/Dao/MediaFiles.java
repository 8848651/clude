package com.media.xczx_plus_media_model.model.Dao;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@TableName("media_files")
public class MediaFiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private Long companyId;
    private String companyName;
    private String filename;
    private String fileType;
    private String tags;
    private String bucket;
    private String filePath;
    private String fileId;
    private String url;
    private String username;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime changeDate;
    private String status;
    private String remark;
    private String auditStatus;
    private String auditMind;
    private Long fileSize;
}
