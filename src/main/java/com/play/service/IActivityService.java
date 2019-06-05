package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.Activity;
import com.play.pojo.User;
import com.play.vo.ActivityImageVo;

import java.util.List;

public interface IActivityService {
    ServerResponse<Activity> create(Activity activity);
    ServerResponse<Activity> modifyActivity(Activity activity, User user);
    ServerResponse<String> deleteActivity(Integer activityId, User user);
    ServerResponse<ActivityImageVo> getActivityInfo(Integer activityId, User user);
    ServerResponse<List<ActivityImageVo>> getActivitiesByUser(String userName, User user, Integer limit, Integer offset);
    ServerResponse<List<ActivityImageVo>> getActivitiesBySelf(User user, Integer limit, Integer offset);
    ServerResponse<List<Activity>> getActivitiesByLocationName(String locationName);
    ServerResponse<List<ActivityImageVo>> getAllActivities(Integer limit, Integer offset, User user);
}
