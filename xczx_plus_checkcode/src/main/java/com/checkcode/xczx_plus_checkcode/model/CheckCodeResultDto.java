package com.checkcode.xczx_plus_checkcode.model;

import lombok.Data;
@Data
public class CheckCodeResultDto {

    //目录
    private String key;

    //图片base64编码
    private String aliasing;
}
