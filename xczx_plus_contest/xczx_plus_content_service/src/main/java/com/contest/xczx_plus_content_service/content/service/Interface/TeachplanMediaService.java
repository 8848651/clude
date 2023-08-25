package com.contest.xczx_plus_content_service.content.service.Interface;

import com.contest.xczx_plus_content_model.content.Dao.TeachplanMedia;
import com.contest.xczx_plus_content_model.content.Pto.AddTeachplanMediaDto;

public interface TeachplanMediaService {

    //添加目录视屏
    TeachplanMedia AddMedia(AddTeachplanMediaDto addTeachplanMediaDto);

    //删除目录绑定视屏
    void DeleteMedia(Long teachid);
}
