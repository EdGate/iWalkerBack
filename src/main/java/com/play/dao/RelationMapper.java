package com.play.dao;

import com.play.pojo.Relation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    List<Relation> showrequest(String receiver);//@Param("receiver")

    List<Relation> findStatus1(String showname);//@Param("showname")

    int deleteByUsername(@Param("namedel") String namedel, @Param("delname") String delname);

    List<Relation> showfriend(String myname);//@Param("myname")

    int getfriendStatus(@Param("applicant") String applicant, @Param("receiver")String receiver);
}