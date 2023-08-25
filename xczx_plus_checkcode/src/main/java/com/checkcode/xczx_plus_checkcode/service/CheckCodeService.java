package com.checkcode.xczx_plus_checkcode.service;
import com.checkcode.xczx_plus_checkcode.model.CheckCodeParamsDto;
import com.checkcode.xczx_plus_checkcode.model.CheckCodeResultDto;

public interface CheckCodeService {


    //生成验证码
     CheckCodeResultDto generate(CheckCodeParamsDto checkCodeParamsDto);

    //校验验证码
    boolean verify(String key, String code);


    //验证码生成器 值
    interface CheckCodeGenerator{
        String generate(int length);
    }

   //key生成器 目录
    interface KeyGenerator{
        String generate(String prefix);
    }


   //验证码存储
    interface CheckCodeStore{
        // 目录 值 秒
        void set(String key, String value, Integer expire);

        String get(String key);

        void remove(String key);
    }
}
