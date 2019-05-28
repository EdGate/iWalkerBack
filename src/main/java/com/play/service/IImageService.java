package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.Image;
import com.play.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    ServerResponse<Image> create(Image image, MultipartFile file, User user);
    ServerResponse<List<Image>> getByActivity(Integer activityId, User user);
    ServerResponse<String> deleteImage(Integer imageId, Integer activityId, User user);
}
