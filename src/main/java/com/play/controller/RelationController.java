package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.IRelationService;
import com.play.service.IUserService;
import com.play.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.play.common.Const.CURRENT_USER;


@Controller
@RequestMapping("/relation/")
public class RelationController {
    @Autowired
    private IRelationService iRelationService;


    //添加好友
    @RequestMapping(value = "addfriend.do", method = RequestMethod.POST)//加判定是否申请过
    @ResponseBody
    public ServerResponse<String> addfriend(String applicant, String receiver, HttpSession session) {
        ServerResponse<String> response = iRelationService.addfriend(applicant,receiver);
        if (response.isSuccess()){
            session.setAttribute(CURRENT_USER, response.getData());
        }
        return response;
    }

    //确认好友添加消息
    @RequestMapping(value = "confirmfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> confirmfriend(String applicant,String receiver,Byte status, HttpSession session) {
        ServerResponse<String> response = iRelationService.confirmfriend(applicant,receiver,status);
        if (response.isSuccess()){
            session.setAttribute(CURRENT_USER, response.getData());
        }
        return response;
    }

    //显示好友请求
    @RequestMapping(value = "showrequest.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Relation>> showrequest(User user, HttpSession session) {
        ServerResponse<List<Relation>> response =iRelationService.showrequest(user);
        if (response.isSuccess()){
            session.setAttribute(CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "showfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<User>> showfriend(User user, HttpSession session) {
        ServerResponse<List<User>> response=iRelationService.showfriend(user);

        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
    //删除好友
    @RequestMapping(value = "delfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delfriend(User user,String delname, HttpSession session) {
        ServerResponse<String> response=iRelationService.delfriend(user,delname);
        if (response.isSuccess()){
            session.setAttribute(CURRENT_USER, response.getData());
        }
        return response;
    }

}
