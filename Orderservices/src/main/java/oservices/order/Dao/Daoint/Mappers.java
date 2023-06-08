package oservices.order.Dao.Daoint;


import org.apache.ibatis.annotations.*;
import oservices.order.Dao.Factory.Usersorder;

import java.util.List;

@Mapper
public interface Mappers {
    @Select("select * from course")
    @Results(id = "UsersMap", value = {
            @Result(property = "KeChengHao", column = "cno"),
            @Result(property = "KeChengMing", column = "cname"),
            @Result(property = "KeChengShiJian", column = "ctime"),
            @Result(property = "KeChengFen", column = "credit")
    })
    List<Usersorder> selectAllUsers();

    @Select("select * from course where cno=${KeChengHao}")
    @ResultMap("UsersMap")
    Usersorder selectOne(@Param("KeChengHao") String KeChengHao);
}
