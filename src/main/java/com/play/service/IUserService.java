package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);

    ServerResponse<User> modify(User user, MultipartFile file);
    ServerResponse<User> get(String userName);
    ServerResponse<User> getFriend(String userName, User user);


    ServerResponse<List<User>> findfriend(User user,String findname);

}
