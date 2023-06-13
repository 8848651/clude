package landing.Dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMappers {
    @Select("select * from MESSAGES")
    @Results(id = "MESSAGES", value = {
            @Result(property = "name", column = "MGE"),
            @Result(property = "message", column = "MSG")
    })
    List<Message> selectAll();

    //插入1个
    @Insert("INSERT INTO MESSAGES (MGE, MSG) VALUES (#{name}, #{message})")
    @ResultMap("MESSAGES")
    int insert(Message Message);

    //查找一个
    @Select("select * from MESSAGES where id=#{id}")
    @ResultMap("MESSAGES")
    Message selectOne1(@Param("id") String id);

    @Select("select * from MESSAGES where MGE=#{name}")
    @ResultMap("MESSAGES")
    List<Message> selectOne2(@Param("name") String name);

    //更新
    @Update("UPDATE MESSAGES SET MGE = #{name} where id=#{id}")
    @ResultMap("MESSAGES")
    int Update1(Message Message);

    @Update("UPDATE MESSAGES SET MSG = #{message} where id=#{id}")
    @ResultMap("MESSAGES")
    int Update2(Message Message);

    //删除
    @Delete("DELETE FROM MESSAGES WHERE id=#{id}")
    int deleteById(@Param("id") String id);
}
