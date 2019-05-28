package com.play.util;

import com.play.common.Const;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {
    public static String getImagePath(MultipartFile file) {
        String pathPrefix = System.getProperty("evan.webapp");
        pathPrefix = pathPrefix.split("target")[0];
        pathPrefix += "uploadFiles/";
        String filename = file.getOriginalFilename();
        String trueFileName = String.valueOf(System.currentTimeMillis())+filename;
        return pathPrefix + trueFileName;
    }

    public static String getImageName(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return Const.FILE_PATH_PREFIX + String.valueOf(System.currentTimeMillis())+filename;
    }
}
