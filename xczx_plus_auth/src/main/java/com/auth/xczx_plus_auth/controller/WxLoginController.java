package com.auth.xczx_plus_auth.controller;
import com.auth.xczx_plus_auth.model.Dao.XcUser;
import com.auth.xczx_plus_auth.model.Dto.AuthParamsDto;
import com.auth.xczx_plus_auth.service.WebrRgister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class WxLoginController {

    @Autowired
    WebrRgister WebrRgister;


    @RequestMapping("/wxLogin")
    public String wxLogin(String code, String state) throws IOException {
        //远程调用微信请令牌，拿到令牌查询用户信息，将用户信息写入本项目数据库
        XcUser xcUser =new XcUser();
        String username = xcUser.getUsername();
        return "redirect:http://www.51xuecheng.cn/sign.html?username=" + username + "&authType=wx";
    }
}
