package com.play.service.impl;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.dao.UserMapper;
import com.play.pojo.User;
import com.play.service.IUserService;
import com.play.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return ServerResponse.createBySucessMessage("注册成功");
    }

    private ServerResponse<String> checkValid(String str){
        int resultCount = userMapper.checkUsername(str);
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        return ServerResponse.createBySucessMessage("校验成功");
    }
}
