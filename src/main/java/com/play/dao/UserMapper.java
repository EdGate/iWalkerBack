package com.play.dao;

import com.play.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int getPrimaryKeyByUsername(String username);

    List<User> findUser(@Param("findname") String findname);

    User findUserByUsername(@Param("findusername") String findusername);

    User showRequestUserbyRelation(String username);

}