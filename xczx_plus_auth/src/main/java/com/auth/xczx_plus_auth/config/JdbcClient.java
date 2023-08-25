package com.auth.xczx_plus_auth.config;

import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现 ClientDetailsService 和 ClientRegistrationService 接口
 */
@Service
@Primary
public class JdbcClient implements ClientDetailsService, ClientRegistrationService {



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

    /**
     * 添加客户端信息。
     * @param clientDetails 要添加的客户端信息。
     * @throws ClientAlreadyExistsException 如果客户端已存在。
     */
    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
    }

    /**
     * 更新客户端信息。
     * @param clientDetails 要更新的客户端信息。
     * @throws NoSuchClientException 如果客户端不存在。
     */
    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    /**
     * 更新客户端密钥。
     * @param s  客户端ID。
     * @param s1 新的客户端密钥。
     * @throws NoSuchClientException 如果客户端不存在。
     */
    @Override
    public void updateClientSecret(String s, String s1) throws NoSuchClientException {

    }

    /**
     * 删除客户端信息。
     * @param s 客户端ID。
     * @throws NoSuchClientException 如果客户端不存在。
     */
    @Override
    public void removeClientDetails(String s) throws NoSuchClientException {

    }

    /**
     * 列出所有客户端信息。
     * @return 客户端信息列表。
     */
    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}