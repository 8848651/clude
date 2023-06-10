package Mybatis.Mapper;

import Mybatis.Mybatis_GJL.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FirmMapper {

    List<Brand> FirmSelectall();

    Brand FirmSelectone(@Param("id") int id);
    Brand FirmSelectone(Brand brand);

    int insert(Brand brand);

    //注解开发
    @Select("select * from tb_brand where id=#{id}")
    @ResultMap("FirmMappers")//应该添加Mapper代理中映射的名字
    Brand SelectById(@Param("id") int id);


    int update(Brand brand);

    void delete(@Param("id")int id);

}
