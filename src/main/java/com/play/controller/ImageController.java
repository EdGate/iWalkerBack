package com.play.controller;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.pojo.Image;
import com.play.pojo.User;
import com.play.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("image")
public class ImageController {

    @Autowired
    private IImageService iImageService;

    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Image> createImage(@RequestParam(value = "file",required = false) MultipartFile file,
                                             Image image, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        image.setId(null);
        image.setCreateTime(null);
        image.setModifyTime(null);
        return iImageService.create(image, file, currentUser);
    }

    // 自己动态的全部图片
    @RequestMapping(value = "get_by_activity.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Image>> getByActivity(Integer activityId, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iImageService.getByActivity(activityId, currentUser);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteImage(Integer imageId, Integer activityId, HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iImageService.deleteImage(imageId, activityId, currentUser);
    }
}
