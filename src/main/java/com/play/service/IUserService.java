package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.User;
import org.springframework.web.multipart.MultipartFile;


public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<User> modify(User user, MultipartFile file);
}
