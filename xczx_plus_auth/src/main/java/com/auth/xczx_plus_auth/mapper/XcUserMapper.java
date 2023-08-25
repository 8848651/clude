package com.auth.xczx_plus_auth.mapper;

import com.auth.xczx_plus_auth.model.Dao.XcUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XcUserMapper extends BaseMapper<XcUser> {

    List<String> GetAuthority(@Param("id") String id);

}
