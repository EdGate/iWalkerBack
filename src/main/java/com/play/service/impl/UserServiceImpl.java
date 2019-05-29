package com.play.service.impl;

import com.play.common.ServerResponse;
import com.play.dao.UserMapper;
import com.play.pojo.User;
import com.play.service.IUserService;
import com.play.util.ImageUtil;
import com.play.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户不存在！");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        // 序列化时不返回密码
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServerResponse<String> register(User user){
        ServerResponse<String> validResponse = this.checkValid(user.getUserName());
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        // MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    private ServerResponse<String> checkValid(String str){
        int resultCount = userMapper.checkUsername(str);
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<User> modify(User user, MultipartFile file) {
        int resultCount = userMapper.checkUsername(user.getUserName());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户不存在！");
        }
        // 设置用户 id
        int id = userMapper.getPrimaryKeyByUsername(user.getUserName());
        if (id != user.getId()) {
            return ServerResponse.createByErrorMessage("无权限操作！");
        }
        // 不能修改
        user.setPassword(null);
        user.setCreateTime(null);
        user.setModifyTime(null);

        // 如果有传头像
        if (file != null) {
            String path = ImageUtil.getImagePath(file);
            user.setImage(ImageUtil.getImageName(file));
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                return ServerResponse.createByErrorMessage("修改信息失败！");
            }
        }

        int resCount = userMapper.updateByPrimaryKeySelective(user);
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("修改信息失败！");
        }
        return ServerResponse.createBySuccessData(user);
    }

    @Override
    public ServerResponse<List<User>> findfriend(String findname){

        List<User> resultUser = userMapper.findUser(findname);
        for(int i=0;i<resultUser.size();i++){
            resultUser.get(i).setPassword(StringUtils.EMPTY);
        }

        if (resultUser == null) {
            return ServerResponse.createByErrorMessage("用户不存在！");
        }
        return ServerResponse.createBySuccess("查询成功", resultUser);
    }


    @Override
    public ServerResponse<User> touchfriend(User user){
        User userinfo=userMapper.findUserByUsername(user.getUserName());
        return ServerResponse.createBySuccess("点击好友成功",userinfo);
    }

    @Override
    public User findUserByUsername(String findusername){
        User userinfo=userMapper.findUserByUsername(findusername);
        return userinfo;
    }


}
