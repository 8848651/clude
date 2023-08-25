package com.auth.xczx_plus_auth.service;
import com.auth.xczx_plus_auth.feign.feignclient;
import com.auth.xczx_plus_auth.mapper.XcUserMapper;
import com.auth.xczx_plus_auth.model.Dao.XcUser;
import com.auth.xczx_plus_auth.model.Dto.AuthParamsDto;
import com.auth.xczx_plus_auth.model.Dto.XcUserExt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("password")
public class WebServiceImpl implements AuthService{

    @Autowired
    XcUserMapper XcUserMapper;
    @Autowired
    feignclient feignclient;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {
        String username = authParamsDto.getUsername();
        //根据 authParamsDto取出数据库
        LambdaQueryWrapper<XcUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(XcUser::getUsername,username);
        XcUser xcUser = XcUserMapper.selectOne(queryWrapper);
        List<String> strings = XcUserMapper.GetAuthority(xcUser.getId());
        XcUserExt xcUserExt = new XcUserExt();
        /*
        String checkcodekey = authParamsDto.getCheckcodekey();
        String checkcode = authParamsDto.getCheckcode();
        Boolean verify = feignclient.verify(checkcodekey, checkcode);
        if (verify==null) {
            throw new RuntimeException("发生熔断");
        }
        if (!verify) {
            throw new RuntimeException("验证码错误");
        }
         */
        BeanUtils.copyProperties(xcUser,xcUserExt);
        xcUserExt.setPermissions(strings);
        //校验密码 取出数据库存储的正确密码进行效验
        String password = xcUser.getPassword();
        String password1 = authParamsDto.getPassword();
        boolean matches = passwordEncoder.matches(password,password1);
        if(!matches){ throw new RuntimeException("账号或密码错误"); }
        return xcUserExt;
    }
}
