package com.checkcode.xczx_plus_checkcode.service;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component("UUIDKeyGenerator")
//目录生成器
public class UUIDKeyGenerator implements CheckCodeService.KeyGenerator {
    @Override
    public String generate(String prefix) {
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid.replaceAll("-", "");
    }
}
