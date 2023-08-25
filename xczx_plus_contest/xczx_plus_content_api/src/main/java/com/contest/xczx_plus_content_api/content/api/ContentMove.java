package com.contest.xczx_plus_content_api.content.api;

import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ContentMove {
    @Autowired
    TeachplanService TlanService;


    /**
     * 向下移动
     */
    @PostMapping("/teachplan/movedown/{id}")
    public void movedown(@PathVariable Long id){
        TlanService.Move(id,true);
    }

    /**
     * 向上移动
     */
    @PostMapping("/teachplan/moveup/{id}")
    public void moveup(@PathVariable Long id){
        TlanService.Move(id,false);
    }



}

