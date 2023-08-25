package com.auth.xczx_plus_auth.service;

import com.auth.xczx_plus_auth.mapper.XcUserMapper;
import com.auth.xczx_plus_auth.mapper.XcUserRoleMapper;
import com.auth.xczx_plus_auth.model.Dao.XcUser;
import com.auth.xczx_plus_auth.model.Dto.AuthParamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WebrRgister {

    @Autowired
    XcUserMapper xcUserMapper;
    @Autowired
    XcUserRoleMapper XcUserRoleMapper;
    @Autowired
    XcUserRoleMapper xcUserRoleMapper;

    public void addUser(AuthParamsDto authParamsDto){
    }
}
