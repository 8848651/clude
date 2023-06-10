package Mybatis.Mapper;
import Mybatis.Mybatis_GJL.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CRUDMapper {
    //查询所有
    List<User> Selectall();
    //多条件精准查询
    User Multicondition_Select_JZ(@Param("StudentNumber") String StudentNumber, @Param("name") String name, @Param("password") String password);
    User Multicondition_Select_JZ(User user);
    User Multicondition_Select_JZ(Map map);
    //多条件模糊查询
    List<User> Multicondition_Select_MH(@Param("StudentNumber") String StudentNumber, @Param("name") String name, @Param("password") String password);
    List<User> Multicondition_Select_MH(User user);
    List<User> Multicondition_Select_MH(Map map);
    //单条件模糊查询
    List<User> Singleconditional_Select_MH(@Param("StudentNumber") String StudentNumber, @Param("name") String name, @Param("password") String password);
    List<User> Singleconditional_Select_MH(User user);
    List<User> Singleconditional_Select_MH(Map map);

    //静态SQL修改
    int staticupdate(@Param("StudentNumber") String StudentNumber, @Param("name") String name, @Param("password") String password);
    int staticupdate(User user);
    int staticupdate(Map map);

    //动态SQL修改
    int dynamicupdate(@Param("StudentNumber") String StudentNumber, @Param("name") String name, @Param("password") String password);
    int dynamicupdate(User user);
    int dynamicupdate(Map map);

    //增加
    void insert(@Param("StudentNumber") String StudentNumber, @Param("name") String name, @Param("password") String password);
    void insert(User user);
    void insert(Map map);

    int deleteone(@Param("StudentNumber") String StudentNumber);
    int deletemany(@Param("StudentNumbers") String[] StudentNumbers);

}
