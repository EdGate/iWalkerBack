package com.play.service.impl;

import com.google.common.collect.Lists;
import com.play.common.ServerResponse;
import com.play.dao.ActivityMapper;
import com.play.dao.ImageMapper;
import com.play.dao.LikeMapper;
import com.play.dao.RelationMapper;
import com.play.pojo.*;
import com.play.service.IActivityService;
import com.play.vo.ActivityImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iActivityService")
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private LikeMapper likeMapper;

    @Override
    public ServerResponse<Activity> create(Activity activity) {
        int resCount = activityMapper.insertSelective(activity);
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("创建失败！");
        }
        Activity retActivity = activityMapper.selectLastByUserName(activity.getUserName());
        if (retActivity == null) {
            return ServerResponse.createByErrorMessage("创建成功但获取信息失败");
        }
        return ServerResponse.createBySuccessData(retActivity);
    }

    @Override
    public ServerResponse<Activity> modifyActivity(Activity activity, User user) {
        activity.setUserName(user.getUserName());

        if (activity.getId() == null) {
            return ServerResponse.createByErrorMessage("缺少参数！");
        }

        if (!activity.getUserName().equals(user.getUserName())) {
            return ServerResponse.createByErrorMessage("无权限操作！");
        }

        // 不能改
        activity.setLikeNum(null);
        activity.setCreateTime(null);
        activity.setModifyTime(null);

        int resCount = activityMapper.updateByPrimaryKeySelective(activity);
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败！");
        }
        Activity retActivity = activityMapper.selectByPrimaryKey(activity.getId());
        return ServerResponse.createBySuccessData(retActivity);
    }

    @Override
    public ServerResponse<String> deleteActivity(Integer activityId, User user) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null || !activity.getUserName().equals(user.getUserName())) {
            return ServerResponse.createByErrorMessage("无权限操作！");
        }

        int resCount = activityMapper.deleteByPrimaryKey(activityId);
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败！");
        }

        return ServerResponse.createBySuccessMessage("删除成功！");
    }

    @Override
    public ServerResponse<ActivityImageVo> getActivityInfo(Integer activityId, User user) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        if (activity == null || !activity.getUserName().equals(user.getUserName())&&
                relationMapper.getfriend(user.getUserName(),activity.getUserName()).getStatus()!=1) {
           //判断是否为好友 1为好友状态
            return ServerResponse.createByErrorMessage("无权限操作");
        }

        ActivityImageVo activityImageVo = createActivityImageVo(activity);
        Like like = likeMapper.getByActivityIDUserName(activityId, user.getId());
        if (like == null) {
            activityImageVo.setLike(false);
        } else {
            activityImageVo.setLike(true);
        }
        return ServerResponse.createBySuccessData(activityImageVo);
    }

    @Override
    public ServerResponse<List<ActivityImageVo>> getActivitiesByUser(String userName, User user,
                                                                     Integer limit, Integer offset) {

        if(!userName.equals(user.getUserName())){//判断是否是本人
            if(relationMapper.getfriend(user.getUserName(),userName).getStatus()!=1){//判断是否为好友 1为好友状态
                return ServerResponse.createByErrorMessage("无权限操作--不是好友");
            }
        }

        List<Activity> activityList = activityMapper.selectByUserName(userName, limit, offset);
        return ServerResponse.createBySuccessData(transformActivitiesToVo(activityList, user));
    }

    @Override
    public ServerResponse<List<Activity>> getActivitiesByLocationName(String locationName) {
        List<Activity> activityList = activityMapper.selectByLocationName(locationName);
        for (Activity activity : activityList) {
            activity.setUserName(null);
            activity.setLikeNum(null);
            activity.setExtra(null);
        }
        return ServerResponse.createBySuccessData(activityList);
    }

    @Override
    public ServerResponse<List<ActivityImageVo>> getAllActivities(Integer limit, Integer offset, User user) {
        List<Relation> relationList = relationMapper.showfriend(user.getUserName());
        // 构造查询的用户名
        List<String> nameList = Lists.newArrayList();
        for (Relation relation : relationList) {
            if (relation.getApplicant().equals(user.getUserName())) {
                nameList.add(relation.getReceiver());
            } else {
                nameList.add(relation.getApplicant());
            }
        }
        nameList.add(user.getUserName());
        List<Activity> activityList = activityMapper.selectByUserNames(nameList, limit, offset);
        return ServerResponse.createBySuccessData(transformActivitiesToVo(activityList, user));
    }

    private List<ActivityImageVo> transformActivitiesToVo(List<Activity> activityList, User user) {
        List<ActivityImageVo> activityImageVoList = Lists.newArrayList();
        for (Activity activity : activityList) {
            ActivityImageVo activityImageVo = createActivityImageVo(activity);
            Like like = likeMapper.getByActivityIDUserName(activity.getId(), user.getId());
            if (like == null) {
                activityImageVo.setLike(false);
            } else {
                activityImageVo.setLike(true);
            }
            activityImageVoList.add(activityImageVo);
        }
        return activityImageVoList;
    }

    private ActivityImageVo createActivityImageVo(Activity activity) {
        List<Image> imageList = imageMapper.selectImageByActivityId(activity.getId());
        ActivityImageVo activityImageVo = new ActivityImageVo();
        activityImageVo.setId(activity.getId());
        activityImageVo.setContent(activity.getContent());
        activityImageVo.setCreateTime(activity.getCreateTime());
        activityImageVo.setExtra(activity.getExtra());
        activityImageVo.setImages(imageList);
        activityImageVo.setModifyTime(activity.getModifyTime());
        activityImageVo.setLikeNum(activity.getLikeNum());
        activityImageVo.setLocation(activity.getLocation());
        activityImageVo.setLocationName(activity.getLocationName());
        activityImageVo.setUserName(activity.getUserName());
        return activityImageVo;
    }

}
