package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.Activity;
import com.play.pojo.Comment;
import com.play.pojo.User;
import com.play.service.ICommentService;
import com.play.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ICommentService iCommentService;

    @RequestMapping(value = "addcomment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addComment(String text,Integer activity, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return  iCommentService.addComment(text,activity,currentUser);
    }

    @RequestMapping(value = "showcomment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Comment>> showcomment(Integer activity,int offset,int limit, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return  iCommentService.showComment(activity,currentUser,offset,limit);
    }
}
