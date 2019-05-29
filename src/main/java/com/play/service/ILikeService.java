package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.Comment;
import com.play.pojo.User;

import java.util.List;


public interface ILikeService {
    ServerResponse<String> like(Integer activityId,User user);
    ServerResponse<String> unlike(Integer activityId,User user);
}
