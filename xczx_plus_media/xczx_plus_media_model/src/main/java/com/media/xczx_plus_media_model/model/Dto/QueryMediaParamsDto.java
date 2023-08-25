package com.media.xczx_plus_media_model.model.Dto;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class QueryMediaParamsDto {
    //媒资文件名称
    private String filename;
    //媒资类型
    private String fileType;
    //审核状态
    private String auditStatus;
}
