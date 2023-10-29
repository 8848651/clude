package com.auth.xczx_plus_auth.config;

import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现 ClientDetailsService 和 ClientRegistrationService 接口
 */
@Service
@Primary
public class JdbcClient implements ClientDetailsService{

    /**
     * 根据客户端ID加载客户端信息。
     * @param ClientId 客户端ID。
     * @return 加载的客户端信息。
     * @throws ClientRegistrationException 如果无法加载客户端信息。
     */
    @Override
    public ClientDetails loadClientByClientId(String ClientId) throws ClientRegistrationException {
        String Clientid="XcWebApp";
        String Secret="XcWebApp";
        String resourceId="xczx_plus_content,xczx_plus_media,xczx_plus_orders,xczx_plus_learning";
        String Scopes="all";
        String GrantTypes="authorization_code,password,client_credentials,implicit,refresh_token";
        String Auto=null;
        String Url="http://www.51xuecheng.cn";

        //客户端ID，资源ID，授权范围，授权类型，自动批准 aut自动 null手动 ，回调URL
        BaseClientDetails clientDetail = new BaseClientDetails(Clientid, resourceId, Scopes,GrantTypes, Auto,Url);
        //秘钥
        clientDetail.setClientSecret(Secret);
        return clientDetail;
    }
}