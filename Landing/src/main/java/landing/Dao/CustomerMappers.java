package landing.Dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMappers {
    @Select("select * from MANAGES")
    @Results(id = "MANAGES", value = {
            @Result(property = "User", column = "USERNAME"),
            @Result(property = "Password", column = "PASSWORD")
    })
    List<Customer> selectAll();

    //增
    @Insert("INSERT INTO MANAGES (USERNAME, PASSWORD) VALUES (#{User}, #{Password})")
    @ResultMap("MANAGES")
    int insert(Customer customer);

    //查
    @Select("select * from MANAGES where USERNAME=#{User}")
    @ResultMap("MANAGES")
    Customer selectOne(@Param("User") String User);

    //删
    @Delete("DELETE FROM MANAGES WHERE USERNAME=#{User}")
    @ResultMap("MANAGES")
    int deleteById(@Param("User") String User);

    //改
    @Update("UPDATE MANAGES SET PASSWORD=#{Password} WHERE USERNAME=#{User}")
    @ResultMap("MANAGES")
    int updateById(Customer customer);
}
