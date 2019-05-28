package com.play.dao;

import com.play.pojo.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    Image selectByActivityIdOrder(@Param("activityId") Integer activityId, @Param("order") Integer order);

    List<Image> selectImageByActivityId(Integer activityId);

    int countImageByActivityId(Integer activityId);
}