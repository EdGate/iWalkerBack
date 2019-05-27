package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
}
