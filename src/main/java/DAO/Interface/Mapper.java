package DAO.Interface;


import DAO.Implement.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Mapper {
    //增
    int insert(User user);
    //删
    void delete(@Param("name")String name);
    //改
    int update(User user);
    //查
    User Select(@Param("name")String name);

}
