package com.play.common;

public class Const {
    /* 常量 */
    public static final String CURRENT_USER = "currectUser";
    public static final String EMAIL = "emial";
    public static final String USERNAME = "username";

    public interface Role {
        int ROLE_CUSTOMER = 0;  // 普通用户
        int ROLE_ADMIN = 1;  // 管理员
    }
}
