package com.play.dao;

import com.play.pojo.Like;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

    int deleteByActivityIDUserName(@Param("activityid")Integer activityid, @Param("userid")Integer userid);

    Like getByActivityIDUserName(@Param("activityid")Integer activityid, @Param("userid")Integer userid);
}