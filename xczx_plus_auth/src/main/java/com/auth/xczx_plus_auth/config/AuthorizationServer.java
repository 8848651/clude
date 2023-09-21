package com.auth.xczx_plus_auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import javax.annotation.Resource;

 @Configuration
 @EnableAuthorizationServer
 //AuthorizationServerConfigurerAdapter授权服务器配置
 public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  @Resource(name="TokenServices")
  private AuthorizationServerTokenServices TokenServices;
  @Autowired
  private JdbcClient JdbcClient;
  @Autowired
  private RedisCodeServices RedisCodeServices;
  @Autowired
  private AuthenticationManager authenticationManager;

  //客户端详情服务
  @Override
  public void configure(ClientDetailsServiceConfigurer clients)throws Exception {
        clients.withClientDetails(JdbcClient);
  }

  //令牌端点的访问配置
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
   endpoints
           .authenticationManager(authenticationManager)//认证管理器
           .authorizationCodeServices(RedisCodeServices)//授权码管理器
           .tokenServices(TokenServices)//令牌管理服务
           .allowedTokenEndpointRequestMethods(HttpMethod.POST);
  }

  //令牌端点的安全配置
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security){
   security
           .tokenKeyAccess("permitAll()") //oauth/token_key是公开
           .checkTokenAccess("permitAll()") //oauth/check_token公开
           .allowFormAuthenticationForClients()	//表单认证（申请令牌）
   ;
  }

}
