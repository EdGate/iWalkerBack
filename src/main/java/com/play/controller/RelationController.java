package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
@RequestMapping("/relation/")
public class RelationController {
    @Autowired
    private IRelationService iRelationService;


    //添加好友
    @RequestMapping(value = "addfriend.do", method = RequestMethod.POST)//加判定是否申请过
    @ResponseBody
    public ServerResponse<String> addfriend(String receiver, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<String> response = iRelationService.addfriend(currentUser.getUserName(),receiver);

        return response;
    }

    //确认好友添加消息
    @RequestMapping(value = "confirmfriend.do", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<String> confirmfriend(String receiver,Byte status, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<String> response = iRelationService.confirmfriend(currentUser.getUserName(),receiver,status);

        return response;
    }

    //显示好友请求
    @RequestMapping(value = "showrequest.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Relation>> showrequest(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<List<Relation>> response =iRelationService.showrequest(currentUser);
        return response;
    }

    //显示好友列表
    @RequestMapping(value = "showfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<User>> showfriend(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<List<User>> response=iRelationService.showfriend(currentUser);

        return response;
    }
    //删除好友
    @RequestMapping(value = "delfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delfriend(String delname, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<String> response=iRelationService.delfriend(currentUser,delname);

        return response;
    }

}
