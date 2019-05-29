package com.play.service;

import com.play.common.ServerResponse;

import com.play.pojo.Activity;
import com.play.pojo.Comment;
import com.play.pojo.User;

import java.util.List;


public interface ICommentService {
    ServerResponse<String> addComment(String text,Integer activityId, User user);
    ServerResponse<List<Comment>> showComment(Integer activityId, User user,int offset,int limit);
}
