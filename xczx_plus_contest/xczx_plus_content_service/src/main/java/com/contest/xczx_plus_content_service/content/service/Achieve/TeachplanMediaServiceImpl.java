package com.contest.xczx_plus_content_service.content.service.Achieve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.contest.xczx_plus_content_model.content.Dao.Teachplan;
import com.contest.xczx_plus_content_model.content.Dao.TeachplanMedia;
import com.contest.xczx_plus_content_model.content.Pto.AddTeachplanMediaDto;
import com.contest.xczx_plus_content_service.content.mapper.TeachplanMediaMapper;
import com.contest.xczx_plus_content_service.content.mapper.TeachplanMapper;
import com.contest.xczx_plus_content_service.content.service.Interface.TeachplanMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeachplanMediaServiceImpl implements TeachplanMediaService {

    @Autowired
    private TeachplanMapper teachplanMapper;
    @Autowired
    private TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public TeachplanMedia AddMedia(AddTeachplanMediaDto addTeachplanMediaDto) {

        Long teachplanId = addTeachplanMediaDto.getTeachplanId();
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        //先删除原来该教学计划绑定的媒资
        LambdaQueryWrapper<TeachplanMedia> MediaLambda = new LambdaQueryWrapper<>();
        MediaLambda.eq(TeachplanMedia::getTeachplanId,teachplanId);
        teachplanMediaMapper.delete(MediaLambda);

        //再添加教学计划与媒资的绑定关系
        TeachplanMedia teachplanMedia = new TeachplanMedia();
        teachplanMedia.setCourseId(teachplan.getCourseId());
        teachplanMedia.setTeachplanId(teachplanId);
        teachplanMedia.setMediaFilename(addTeachplanMediaDto.getFileName());
        teachplanMedia.setMediaId(addTeachplanMediaDto.getMediaId());
        teachplanMedia.setCreateDate(LocalDateTime.now());
        teachplanMediaMapper.insert(teachplanMedia);
        return teachplanMedia;
    }

    @Override
    public void DeleteMedia(Long teachid) {
        System.out.println(teachid);
        LambdaQueryWrapper<TeachplanMedia> MediaLambda = new LambdaQueryWrapper<>();
        MediaLambda.eq(TeachplanMedia::getTeachplanId,teachid);
        teachplanMediaMapper.delete(MediaLambda);
    }


}
