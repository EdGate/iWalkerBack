package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.Activity;
import com.play.pojo.User;
import com.play.service.IActivityService;
import com.play.vo.ActivityImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private IActivityService iActivityService;

    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Activity> createActivity(Activity activity, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        activity.setUserName(currentUser.getUserName());
        activity.setLikeNum(0);
        activity.setCreateTime(null);
        activity.setModifyTime(null);
        activity.setId(null);
        return iActivityService.create(activity);
    }

    @RequestMapping(value = "modify.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Activity> modifyActivity(Activity activity, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iActivityService.modifyActivity(activity, currentUser);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteActivity(Integer activityId, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iActivityService.deleteActivity(activityId, currentUser);
    }

    @RequestMapping(value = "get_activity_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<ActivityImageVo> getActivityInfo(Integer activityId, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iActivityService.getActivityInfo(activityId, currentUser);
    }

    @RequestMapping(value = "get_activities_by_user.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<ActivityImageVo>> getActivitiesByUser(String userName, Integer limit, Integer offset,
                                                                     HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iActivityService.getActivitiesByUser(userName, currentUser, limit, offset);
    }


    @RequestMapping(value = "get_activities_by_location_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Activity>> getActivitiesByLocationName(String locationName, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iActivityService.getActivitiesByLocationName(locationName);
    }

    @RequestMapping(value = "get_all_activities.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<ActivityImageVo>> getAllActivities(Integer limit, Integer offset, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iActivityService.getAllActivities(limit, offset, currentUser);
    }
}
