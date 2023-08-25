package com.media.xczx_plus_media_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.media.xczx_plus_media_model.model.Dao.MediaProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MediaProcessMapper extends BaseMapper<MediaProcess> {

    List<MediaProcess> SelectList(@Param("total") Integer Total,@Param("index") Integer Index);

    int UpdateLocks(@Param("id") Long id);

}
