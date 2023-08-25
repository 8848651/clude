package com.auth.xczx_plus_auth.config;
import com.alibaba.fastjson.JSON;
import com.auth.xczx_plus_auth.model.Dto.AuthParamsDto;
import com.auth.xczx_plus_auth.model.Dto.XcUserExt;
import com.auth.xczx_plus_auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationContext ApplicationContext;

    //根据账号取出密码和传过来的比对
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AuthParamsDto authParamsDto = JSON.parseObject(name, AuthParamsDto.class);
        String authType = authParamsDto.getAuthType();
        AuthService authService = ApplicationContext.getBean(authType, AuthService.class);
        XcUserExt execute = authService.execute(authParamsDto);
        String password = execute.getPassword();
        execute.setPassword(null);
        String string = JSON.toJSONString(execute);
        List<String> list = execute.getPermissions();
        String[] array = list.toArray(new String[0]);
        UserDetails userDetails = User.withUsername(string)
                .password(password)
                .authorities(array)
                .build();
        return userDetails;
    }


}

