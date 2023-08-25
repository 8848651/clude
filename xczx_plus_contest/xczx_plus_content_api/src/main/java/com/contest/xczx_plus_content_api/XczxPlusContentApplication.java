package com.contest.xczx_plus_content_api;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableSwagger2Doc
@SpringBootApplication(scanBasePackages = {"com.contest","com.messdk"})
@EnableFeignClients("com.contest")
@EnableTransactionManagement
public class XczxPlusContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(XczxPlusContentApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    //动态选择负载均衡算法
    @Bean
    public IRule RandomRule() {
        return new RandomRule();
    }
}
