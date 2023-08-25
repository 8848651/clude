package com.checkcode.xczx_plus_checkcode.service;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component("CodeGenerator")
//验证码生成器
public class CodeGenerator implements CheckCodeService.CheckCodeGenerator {

    @Override
    public String generate(int length) {
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


}
