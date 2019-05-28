package com.play.dao;

import com.play.pojo.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    Activity selectLastByUserName(String username);

    int countByUserNameId(@Param("id") Integer id, @Param("username") String username);

    List<Activity> selectByUserName(@Param("username") String username, @Param("limit") Integer limit,
                                    @Param("offset") Integer offset);
}