package com.auth.xczx_plus_auth.feign;

import com.auth.xczx_plus_auth.model.Dto.PostTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gatway",fallback = Fallback.class)
public interface feignclient {

    //验证码是否正确
    @GetMapping(value = "/checkcode/verify")
    Boolean verify(@RequestParam("key") String key, @RequestParam("code") String code);

    //测试
    @PostMapping(value = "/checkcode/Post")
    Boolean Post(PostTest PostTest);

}
