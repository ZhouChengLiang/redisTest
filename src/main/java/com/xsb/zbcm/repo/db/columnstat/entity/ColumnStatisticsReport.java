package com.xsb.zbcm.repo.db.columnstat.entity;

import java.util.Date;

public class ColumnStatisticsReport {
    private Integer columnId;

    private Integer totalReadNum;

    private Integer pointNum;

    private Integer originalReadNum;

    private Integer keepNum;

    private Integer commentNum;

    private Integer shareNum;

    private Integer totalTipAmount;

    private Integer subscribesCount;

    private Date statisticTime;

    private Integer appPv;

    private Integer webPv;

    private Integer appReadNum;

    private Integer webReadNum;

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getTotalReadNum() {
        return totalReadNum;
    }

    public void setTotalReadNum(Integer totalReadNum) {
        this.totalReadNum = totalReadNum;
    }

    public Integer getPointNum() {
        return pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    public Integer getOriginalReadNum() {
        return originalReadNum;
    }

    public void setOriginalReadNum(Integer originalReadNum) {
        this.originalReadNum = originalReadNum;
    }

    public Integer getKeepNum() {
        return keepNum;
    }

    public void setKeepNum(Integer keepNum) {
        this.keepNum = keepNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getTotalTipAmount() {
        return totalTipAmount;
    }

    public void setTotalTipAmount(Integer totalTipAmount) {
        this.totalTipAmount = totalTipAmount;
    }

    public Integer getSubscribesCount() {
        return subscribesCount;
    }

    public void setSubscribesCount(Integer subscribesCount) {
        this.subscribesCount = subscribesCount;
    }

    public Date getStatisticTime() {
        return statisticTime;
    }

    public void setStatisticTime(Date statisticTime) {
        this.statisticTime = statisticTime;
    }

    public Integer getAppPv() {
        return appPv;
    }

    public void setAppPv(Integer appPv) {
        this.appPv = appPv;
    }

    public Integer getWebPv() {
        return webPv;
    }

    public void setWebPv(Integer webPv) {
        this.webPv = webPv;
    }

    public Integer getAppReadNum() {
        return appReadNum;
    }

    public void setAppReadNum(Integer appReadNum) {
        this.appReadNum = appReadNum;
    }

    public Integer getWebReadNum() {
        return webReadNum;
    }

    public void setWebReadNum(Integer webReadNum) {
        this.webReadNum = webReadNum;
    }
}