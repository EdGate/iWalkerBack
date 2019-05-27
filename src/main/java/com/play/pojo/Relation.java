package com.play.pojo;

import java.util.Date;

public class Relation {
    private Integer id;

    private String applicant;

    private String receiver;

    private Byte status;

    private String extra;

    private Date createTime;

    private Date modifyTime;

    public Relation(Integer id, String applicant, String receiver, Byte status, String extra, Date createTime, Date modifyTime) {
        this.id = id;
        this.applicant = applicant;
        this.receiver = receiver;
        this.status = status;
        this.extra = extra;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Relation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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