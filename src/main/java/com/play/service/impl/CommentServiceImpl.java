package com.play.service.impl;


import com.play.common.ServerResponse;
import com.play.dao.ActivityMapper;
import com.play.dao.CommentMapper;


import com.play.dao.RelationMapper;
import com.play.pojo.Activity;
import com.play.pojo.Comment;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ServerResponse<String> addComment(String text,Integer activityId, User user){
        //好友判断
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        ServerResponse<String> response= friendjudge(activityId,user);
        if(!response.isSuccess()){
            return response;
        }

        Comment comment= new Comment();
        comment.setActivityId(activity.getId());
        comment.setContent(text);
        comment.setUserName(user.getUserName());
        comment.setCreateTime(null);
        comment.setModifyTime(null);
        int onecomment=commentMapper.insertSelective(comment);
        activity.setLikeNum(activity.getLikeNum()+1);
        if( onecomment==0){
            return ServerResponse.createByErrorMessage("评论失败");
        }
        return ServerResponse.createBySuccessData("评论成功");
    }

    @Override
    public ServerResponse<List<Comment>> showComment(Integer activityId, User user,int offset,int limit){
        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        if(!friendjudge(activityId,user).isSuccess()){
            return ServerResponse.createByErrorMessage("无此权限");
        }

        List<Comment> comments = commentMapper.showComment(activity.getId(),offset,limit);
        if(comments==null){
            return ServerResponse.createBySuccessMessage("无评论");
        }
        for(Comment onecommet:comments){
            onecommet.setExtra(null);
            onecommet.setModifyTime(null);
        }
        return ServerResponse.createBySuccess("查询成功", comments);
    }


    private ServerResponse<String> friendjudge(Integer activityId,User user){
        return getStringServerResponse(activityId, user, activityMapper, relationMapper);
    }

    static ServerResponse<String> getStringServerResponse(Integer activityId, User user, ActivityMapper activityMapper, RelationMapper relationMapper) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if(activity == null ){
            return ServerResponse.createByErrorMessage("无此动态");
        }
        Relation relation= relationMapper.getfriend(user.getUserName(),activity.getUserName());
        if(relation==null){
            return ServerResponse.createByErrorMessage("无权限操作");
        }

        if (!activity.getUserName().equals(user.getUserName())&&relation.getStatus()!=1) {
            //判断是否为好友 1为好友状态
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        return ServerResponse.createBySuccessMessage("成功");
    }
}
