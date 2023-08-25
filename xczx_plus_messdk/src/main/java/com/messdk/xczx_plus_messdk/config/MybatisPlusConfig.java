package com.messdk.xczx_plus_messdk.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("MybatisPlusMessdk")
@MapperScan("com.messdk.xczx_plus_messdk.mapper")
public class MybatisPlusConfig {}