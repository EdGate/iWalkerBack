package com.play.service.impl;

import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.dao.ActivityMapper;
import com.play.dao.ImageMapper;
import com.play.pojo.Activity;
import com.play.pojo.Image;
import com.play.pojo.User;
import com.play.service.IImageService;
import com.play.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("iImageService")
public class ImageServiceImpl implements IImageService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ImageMapper imageMapper;

    @Override
    public ServerResponse<Image> create(Image image, MultipartFile file, User user) {
        if (file == null) {
            return ServerResponse.createByErrorMessage("文件不存在！");
        }
        // 验证动态 id 是否是当前用户的动态
        int resCount = activityMapper.countByUserNameId(image.getActivityId(), user.getUserName());
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("没有操作权限！");
        }

        // 是否已上传到最大图片数
        int imageCount = imageMapper.countImageByActivityId(image.getActivityId());
        if (imageCount == Const.MAX_UPLOAD_NUM) {
            return ServerResponse.createByErrorMessage("该动态已到最大上传图片数！");
        }

        // 保存文件
        String path = ImageUtil.getImagePath(file);
        image.setImage(ImageUtil.getImageName());
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            return ServerResponse.createByErrorMessage("图片上传失败！");
        }

        resCount = imageMapper.insertSelective(image);
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("图片上传失败！");
        }

        Image retImage = imageMapper.selectByActivityIdOrder(image.getActivityId(), image.getOrder());
        retImage.setImage(ImageUtil.getFullImagePath(retImage.getImage()));
        return ServerResponse.createBySuccessData(retImage);
    }

    @Override
    public ServerResponse<List<Image>> getByActivity(Integer activityId, User user) {
        // 验证动态 id 是否是当前用户的动态
        int resCount = activityMapper.countByUserNameId(activityId, user.getUserName());
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("没有操作权限！");
        }
        List<Image> imageList = imageMapper.selectImageByActivityId(activityId);
        for (Image image: imageList) {
            image.setImage(ImageUtil.getFullImagePath(image.getImage()));
        }
        return ServerResponse.createBySuccessData(imageList);
    }

    @Override
    public ServerResponse<String> deleteImage(Integer imageId, Integer activityId, User user) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null || !activity.getUserName().equals(user.getUserName())) {
            return ServerResponse.createByErrorMessage("无权限操作！");
        }
        Image image = imageMapper.selectByPrimaryKey(imageId);
        if (image == null || !image.getActivityId().equals(activityId)) {
            return ServerResponse.createByErrorMessage("无权限操作！");
        }
        int resCount = imageMapper.deleteByPrimaryKey(imageId);
        if (resCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败！");
        }
        return ServerResponse.createBySuccessMessage("删除成功！");
    }
}
