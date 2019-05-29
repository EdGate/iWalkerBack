package com.play.service.impl;


import com.play.common.ServerResponse;
import com.play.dao.ActivityMapper;

import com.play.dao.LikeMapper;
import com.play.dao.RelationMapper;
import com.play.pojo.Activity;
import com.play.pojo.Like;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.play.service.impl.CommentServiceImpl.getStringServerResponse;


@Service("iLikeService")
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public ServerResponse<String> like(Integer activityId,User user){
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        ServerResponse<String> response= friendjudge(activityId,user);
        if(!response.isSuccess()){
            return response;
        }
        Like testlike=likeMapper.getByActivityIDUserName(activity.getId(),user.getId());

        if(testlike!=null){
            return ServerResponse.createBySuccessData("已存在此点赞信息");
        }
        Like like= new Like();
        like.setActivityId(activity.getId());
        like.setUserId(user.getId());
        like.setCreateTime(null);
        like.setModifyTime(null);
        int onelike=likeMapper.insertSelective(like);

        activity.setLikeNum(activity.getLikeNum()+1);
        int oneactivity=activityMapper.updateByPrimaryKeySelective(activity);

        if( onelike==0||oneactivity==0){
            return ServerResponse.createByErrorMessage("点赞失败");
        }
        return ServerResponse.createBySuccessData("点赞成功");
    }

    @Override
    public ServerResponse<String> unlike(Integer activityId,User user){

        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        ServerResponse<String> response= friendjudge(activityId,user);
        if(!response.isSuccess()){
            return response;
        }

        Like testlike=likeMapper.getByActivityIDUserName(activity.getId(),user.getId());
        if(testlike==null){
            return ServerResponse.createBySuccessData("不存在此点赞信息");
        }
        int onelike=likeMapper.deleteByActivityIDUserName(activity.getId(),user.getId());

        activity.setLikeNum(activity.getLikeNum()-1);
        int oneactivity=activityMapper.updateByPrimaryKeySelective(activity);

        if( onelike==0||oneactivity==0){
            return ServerResponse.createByErrorMessage("取消点赞失败");
        }
        return ServerResponse.createBySuccessData("取消点赞成功");
    }
    private ServerResponse<String> friendjudge(Integer activityId,User user){
        return getStringServerResponse(activityId, user, activityMapper, relationMapper);
    }

}
