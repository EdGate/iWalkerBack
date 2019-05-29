package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.User;
import com.play.service.IUserService;
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


@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    // 登录
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    // 注册
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    // 修改用户信息
    @RequestMapping(value = "modify.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> modify(@RequestParam(value = "file",required = false) MultipartFile file, User user,
                                       HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        // 用户名与 id 不能修改
        user.setId(currentUser.getId());
        user.setUserName(currentUser.getUserName());
        return iUserService.modify(user, file);
    }

    // 登出
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    // 获取用户信息
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return ServerResponse.createBySuccessData(currentUser);
    }

    //好友搜索
    @RequestMapping(value = "findfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<User>> findfriend(String findname, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<List<User>> response = iUserService.findfriend(findname);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }



    //点击好友
    @RequestMapping(value = "touchfriend.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> touchfriend(User user,HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<User> response=iUserService.touchfriend(user);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
}
