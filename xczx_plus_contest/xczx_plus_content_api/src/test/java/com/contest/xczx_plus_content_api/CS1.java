package com.contest.xczx_plus_content_api;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;

public class CS1 {


    @Test
    void test01(){
        ClassLoader classLoader =XczxPlusContentApplication.class.getClassLoader();
        URL resource = classLoader.getResource("com/contest");
        String path =resource.getPath();
        System.out.println(path);
        String classpath = this.getClass().getResource("/").getPath();
        System.out.println(classpath);
    }

    @Test
    void test02(){
        System.out.println(LocalDateTime.now());
    }
}
