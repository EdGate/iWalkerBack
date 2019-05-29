package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.Activity;

import com.play.pojo.User;

import com.play.service.ILikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("like")
public class LikeController {

    @Autowired
    private ILikeService iLikeService;

    @RequestMapping(value = "like.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> like(Integer activity, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return  iLikeService.like(activity,currentUser);
    }

    @RequestMapping(value = "unlike.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> unlike(Integer activity, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return  iLikeService.unlike(activity,currentUser);
    }
}
