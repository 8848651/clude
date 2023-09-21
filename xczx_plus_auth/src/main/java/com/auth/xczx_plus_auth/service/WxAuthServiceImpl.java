package com.auth.xczx_plus_auth.service;

import com.alibaba.fastjson.JSON;
import com.auth.xczx_plus_auth.mapper.XcUserMapper;
import com.auth.xczx_plus_auth.mapper.XcUserRoleMapper;
import com.auth.xczx_plus_auth.model.Dao.XcUser;
import com.auth.xczx_plus_auth.model.Dao.XcUserRole;
import com.auth.xczx_plus_auth.model.Dto.AuthParamsDto;
import com.auth.xczx_plus_auth.model.Dto.XcUserExt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Service("wx")
public class WxAuthServiceImpl implements AuthService{

    @Autowired
    XcUserMapper xcUserMapper;
    @Autowired
    XcUserRoleMapper xcUserRoleMapper;
    @Autowired
    WxAuthServiceImpl currentPorxy;
    @Autowired
    RestTemplate restTemplate;


    @Value("${weixin.appid}")
    String appid;
    @Value("${weixin.secret}")
    String secret;


    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {
        return null;
    }

    public XcUser wxAuth(String code) {
        //申请令牌
        Map<String, String> access_token_map = getAccess_token(code);
        //访问令牌
        String access_token = access_token_map.get("access_token");
        String openid = access_token_map.get("openid");
        //携带令牌查询用户信息
        Map<String, String> userinfo = getUserinfo(access_token, openid);
        // 保存用户信息到数据库
        XcUser xcUser = currentPorxy.addWxUser(userinfo);
        return xcUser;
    }

    public XcUser addWxUser(Map<String,String> userInfo_map){
        String unionid = userInfo_map.get("unionid");
        String nickname = userInfo_map.get("nickname");
        //根据unionid查询用户信息
        //向数据库新增记录
        //向用户角色关系表新增记录
        return null;

    }

    /**
     * 携带授权码申请令牌
     */
    private Map<String,String> getAccess_token(String code){

        String url_template = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        //最终的请求路径
        String url = String.format(url_template, appid, secret, code);
        //远程调用此url
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        //获取响应的结果
        String result = exchange.getBody();
        //将result转成map
        Map<String,String> map = JSON.parseObject(result, Map.class);
        return map;


    }

    /**
     * 携带令牌查询用户信息
     */
    private Map<String,String> getUserinfo(String access_token,String openid){

        String url_template = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=%s" +
                "&openid=%s";
        String url = String.format(url_template, access_token, openid);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //获取响应的结果
        String result = new String(exchange.getBody().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        //将result转成map
        Map<String,String> map = JSON.parseObject(result, Map.class);
        return map;

    }
}
