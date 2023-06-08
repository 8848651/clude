package userservices.users.Dao.Daoint;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface Mappers {
    @Select("select * from score")
    @Results(id = "UsersMap", value = {
            @Result(property = "snos", column = "sno"),
            @Result(property = "tnos", column = "tno"),
            @Result(property = "cnos", column = "cno"),
            @Result(property = "grades", column = "grade")
    })
    List<Userservice> selectAllUsers();

    @Select("select * from score where cno=${cnos}")
    @ResultMap("UsersMap")
    List<Userservice> selectOne(@Param("cnos") String cnos);
}
