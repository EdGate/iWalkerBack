package com.play.util;

import com.play.common.Const;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {
    private static String trueFileName;
    public static String getImagePath(MultipartFile file) {
        String pathPrefix = System.getProperty("evan.webapp");
        pathPrefix = pathPrefix.split("target")[0];
        pathPrefix += "uploadFiles/";
        String filename = file.getOriginalFilename();
        trueFileName = String.valueOf(System.currentTimeMillis())+filename;
        return pathPrefix + trueFileName;
    }

    public static String getImageName() {
        return trueFileName;
    }

    public static String getFullImagePath(String name) {
        if (name != null){
            return Const.FILE_PATH_PREFIX + name;
        }
        return null;
    }
}
