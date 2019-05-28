package com.play.pojo;

import java.util.Date;

public class Like {
    private Integer id;

    private Integer userId;

    private Integer activityId;

    private String extra;

    private Date createTime;

    private Date modifyTime;

    public Like(Integer id, Integer userId, Integer activityId, String extra, Date createTime, Date modifyTime) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.extra = extra;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Like() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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