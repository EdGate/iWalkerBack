package com.play.pojo;

import java.util.Date;

public class Image {
    private Integer id;

    private Integer activityId;

    private String image;

    private Integer order;

    private String extra;

    private Date createTime;

    private Date modifyTime;

    public Image(Integer id, Integer activityId, String image, Integer order, String extra, Date createTime, Date modifyTime) {
        this.id = id;
        this.activityId = activityId;
        this.image = image;
        this.order = order;
        this.extra = extra;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Image() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}