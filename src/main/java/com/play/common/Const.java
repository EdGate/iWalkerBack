package com.play.common;

public class Const {
    /* 常量 */
    public static final String CURRENT_USER = "currectUser";
    public static final String FILE_PATH_PREFIX = "http://localhost:9969/";
    public static final Integer MAX_UPLOAD_NUM = 9;

    public static interface RELATION_STATUS {
        public static final byte APPLICANT = 0;
        public static final byte IS_FRIEND = 1;
        public static final byte REFUSE = 2;
        public static final byte DELETE= 3;

    }
}
