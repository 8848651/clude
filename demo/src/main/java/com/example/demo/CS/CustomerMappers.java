package com.example.demo.CS;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMappers {
    @Select("select * from MANAGES")
    List<Customer> selectAll();

    //查找一个
    @Select("select * from MANAGES where USERNAME=#{USERNAMES}")
    Customer selectOne(@Param("USERNAMES") String USERNAMES);

    //查找一个
    @Select("select * from MANAGES where USERNAME=#{USERNAMES} for update")
    Customer selecttwo(@Param("USERNAMES") String USERNAMES);

    //插入1个
    @Insert("INSERT INTO MANAGES (USERNAME, PASSWORD) VALUES (#{USERNAMES}, #{PASSWORDS})")
    int insert(@Param("PASSWORDS") String PASSWORDS,@Param("USERNAMES") String USERNAMES);

    //修改一个
    @Update("UPDATE MANAGES SET PASSWORD=#{PASSWORDS} where USERNAME=#{USERNAMES}")
    int update(@Param("PASSWORDS") String PASSWORDS,@Param("USERNAMES") String USERNAMES);
}
