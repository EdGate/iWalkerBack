package com.play.common;

public class Const {
    /* 常量 */
    public static final String CURRENT_USER = "currectUser";
    public static final String FILE_PATH_PREFIX = "http://10.120.173.204:9969/";
    public static final Integer MAX_UPLOAD_NUM = 9;

    public static interface RELATION_STATUS {
        byte APPLICANT = 0;
        byte IS_FRIEND = 1;
        byte REFUSE = 2;
        byte DELETE= 3;

    }
}
