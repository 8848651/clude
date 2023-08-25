package com.contest.xczx_plus_content_service.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.contest.xczx_plus_content_model.content.Dao.Teachplan;
import com.contest.xczx_plus_content_model.content.Pto.TreeTeachplanDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    List<TreeTeachplanDto> SelectTeachplan(@Param("CourseId") Long CourseId);

    void UpdateBatch(@Param("Mylist") List<Teachplan> MoveTemp);
}
