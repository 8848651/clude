package com.checkcode.xczx_plus_checkcode.controller;
import com.checkcode.xczx_plus_checkcode.model.CheckCodeParamsDto;
import com.checkcode.xczx_plus_checkcode.model.CheckCodeResultDto;
import com.checkcode.xczx_plus_checkcode.model.PostTest;
import com.checkcode.xczx_plus_checkcode.service.CheckCodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "*")
public class CheckCodeController {

    @Resource(name = "PicCheckCodeService")
    private CheckCodeService picCheckCodeService;

    @PostMapping(value = "/Phone")
    public CheckCodeResultDto generatePhoneCheckCode(CheckCodeParamsDto checkCodeParamsDto){
        return picCheckCodeService.generate(checkCodeParamsDto);
    }


    @PostMapping(value = "/pic")
    public CheckCodeResultDto generatePicCheckCode(CheckCodeParamsDto checkCodeParamsDto){
        return picCheckCodeService.generate(checkCodeParamsDto);
    }

    @GetMapping(value = "/verify")
    public Boolean verify(@RequestParam("key") String key,@RequestParam("code") String code){
        Boolean isSuccess = picCheckCodeService.verify(key,code);
        return  isSuccess;
    }

    @PostMapping(value = "/Post")
    Boolean Post(@RequestBody PostTest PostTest){
        System.out.println(PostTest);
        return true;
    }
}
