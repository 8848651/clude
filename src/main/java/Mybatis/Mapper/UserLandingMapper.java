package Mybatis.Mapper;

import Mybatis.Mybatis_GJL.UserLoading;
import org.apache.ibatis.annotations.Param;

public interface UserLandingMapper {
    UserLoading Loadingpassword(@Param("name")String name);
    UserLoading Loadingpassword(UserLoading userLoading);
    void insert(UserLoading userLoading);
    int deleteone(@Param("name")String name);
    int update(UserLoading userLoading);
}
