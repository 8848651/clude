package com.auth.xczx_plus_auth;

import com.auth.xczx_plus_auth.feign.feignclient;
import com.auth.xczx_plus_auth.mapper.XcMenuMapper;
import com.auth.xczx_plus_auth.mapper.XcUserMapper;
import com.auth.xczx_plus_auth.mapper.XcUserRoleMapper;
import com.auth.xczx_plus_auth.model.Dao.XcMenu;
import com.auth.xczx_plus_auth.model.Dao.XcUser;
import com.auth.xczx_plus_auth.model.Dao.XcUserRole;
import com.auth.xczx_plus_auth.model.Dto.PostTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class XczxPlusCheckcodeApplicationTests {

    @Autowired
    feignclient feignclient;
    @Autowired
    XcUserMapper XcUserMapper;
    @Autowired
    XcUserRoleMapper XcUserRoleMapper;
    @Autowired
    XcMenuMapper XcMenuMapper;

    @Test
    void contextLoads1() {
        XcUser xcUser = XcUserMapper.selectById(4);
        xcUser.setName("N老师");
        xcUser.setCompanyId("1");
        XcUserMapper.updateById(xcUser);
    }

    @Test
    void contextLoads2() {
        XcUser xcUser = XcUserMapper.selectById("51");
        xcUser.setId("6");
        xcUser.setUsername("789");
        xcUser.setPassword("789");
        xcUser.setCreateTime(LocalDateTime.now());
        System.out.println(xcUser);
        xcUser.setName("学生4");
        XcUserRole XcUserRole=new XcUserRole();
        XcUserRole.setId("6");
        XcUserRole.setUserId(xcUser.getId());
        XcUserRole.setRoleId("17");
        XcUserRole.setCreator("学生");
        XcUserMapper.insert(xcUser);
        XcUserRoleMapper.insert(XcUserRole);
    }

    @Test
    void contextLoads3() {
        List<XcMenu> xcMenus = XcMenuMapper.selectList(null);
        for (XcMenu XcMenu:xcMenus) {
            System.out.println(XcMenu.getCode()+"                "+XcMenu.getMenuName());
        }
    }

}
