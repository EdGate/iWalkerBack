package com.play.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private String userName;

    private Integer activityId;

    private String content;

    private String extra;

    private Date createTime;

    private Date modifyTime;

    public Comment(Integer id, String userName, Integer activityId, String content, String extra, Date createTime, Date modifyTime) {
        this.id = id;
        this.userName = userName;
        this.activityId = activityId;
        this.content = content;
        this.extra = extra;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Comment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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