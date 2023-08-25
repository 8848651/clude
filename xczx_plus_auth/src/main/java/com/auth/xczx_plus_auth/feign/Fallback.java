package com.auth.xczx_plus_auth.feign;
import com.auth.xczx_plus_auth.model.Dto.PostTest;
import org.springframework.stereotype.Component;

@Component
public class Fallback implements feignclient {

    @Override
    public Boolean verify(String key, String code){
        return null;
    }

    @Override
    public Boolean Post(PostTest PostTest) {
        return null;
    }
}
