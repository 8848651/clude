package com.auth.xczx_plus_auth.model.Dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AuthParamsDto {

    private String username;
    private String password;
    private String cellphone;
    private String checkcode;
    private String checkcodekey;
    private String authType;
    private Map<String, Object> payload = new HashMap<>();
}
