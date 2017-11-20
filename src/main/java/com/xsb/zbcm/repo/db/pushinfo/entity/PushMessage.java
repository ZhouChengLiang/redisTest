package com.xsb.zbcm.repo.db.pushinfo.entity;

import java.util.Date;

public class PushMessage {
    private Integer id;

    private Integer groups;

    private Integer targetType;

    private Date pushTime;

    private String title;

    private String content;

    private Integer articleId;

    private Integer createUserId;

    private String createUserName;

    private Integer status;

    private Date createTime;

    private Integer messageType;

    private String url;

    private Integer innerMessageType;

    private Integer countyCode;

    private String countyName;

    private Integer linkrelationType;

    private String pushPersons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInnerMessageType() {
        return innerMessageType;
    }

    public void setInnerMessageType(Integer innerMessageType) {
        this.innerMessageType = innerMessageType;
    }

    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getLinkrelationType() {
        return linkrelationType;
    }

    public void setLinkrelationType(Integer linkrelationType) {
        this.linkrelationType = linkrelationType;
    }

    public String getPushPersons() {
        return pushPersons;
    }

    public void setPushPersons(String pushPersons) {
        this.pushPersons = pushPersons;
    }
}