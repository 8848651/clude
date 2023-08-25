package com.learning.xczx_plus_learning_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.learning")
@EnableFeignClients("com.learning")
public class XczxPlusLearningApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XczxPlusLearningApiApplication.class, args);
    }

}
