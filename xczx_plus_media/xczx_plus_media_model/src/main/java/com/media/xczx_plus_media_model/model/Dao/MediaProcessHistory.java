package com.media.xczx_plus_media_model.model.Dao;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@TableName("media_process_history")
public class MediaProcessHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String fileId;
    private String filename;
    private String bucket;
    private String filePath;
    private String status;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime finishDate;
    private String url;
    private String errormsg;
    private int failCount;
}
