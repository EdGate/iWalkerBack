package com.play.pojo;

import java.util.Date;

public class Activity {
    private Integer id;

    private String userName;

    private String content;

    private String location;

    private String locationName;

    private Integer likeNum;

    private String extra;

    private Date createTime;

    private Date modifyTime;

    public Activity(Integer id, String userName, String content, String location, String locationName, Integer likeNum, String extra, Date createTime, Date modifyTime) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.location = location;
        this.locationName = locationName;
        this.likeNum = likeNum;
        this.extra = extra;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Activity() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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