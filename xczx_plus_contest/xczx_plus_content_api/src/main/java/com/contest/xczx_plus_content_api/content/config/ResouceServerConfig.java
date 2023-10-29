package com.contest.xczx_plus_content_api.content.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
 @Configuration
 @EnableResourceServer
 @EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
 public class ResouceServerConfig extends ResourceServerConfigurerAdapter {


  //资源服务标识
  public static final String RESOURCE_ID = "xczx_plus_content";

  @Autowired
  TokenStore tokenStore;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
   resources.resourceId(RESOURCE_ID)//资源服务器的资源ID
           .tokenStore(tokenStore)//令牌存储方式
           .stateless(true);//不存会话信息
  }

 @Override
 public void configure(HttpSecurity http) throws Exception {
  http
          .csrf()
          .disable()
          .authorizeRequests()
          .anyRequest()
          .permitAll();
 }
}
