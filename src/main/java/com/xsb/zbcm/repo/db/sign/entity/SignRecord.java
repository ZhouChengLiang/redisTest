package com.xsb.zbcm.repo.db.sign.entity;

public class SignRecord {
    private Integer userid;

    private String lastdate;

    private Integer serialdays;

    private Integer countyCode;

    private Integer signType;

    private Integer signDays;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public Integer getSerialdays() {
        return serialdays;
    }

    public void setSerialdays(Integer serialdays) {
        this.serialdays = serialdays;
    }

    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public Integer getSignDays() {
        return signDays;
    }

    public void setSignDays(Integer signDays) {
        this.signDays = signDays;
    }
}