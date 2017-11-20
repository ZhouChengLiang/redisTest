package com.xsb.zbcm.repo.db.grade.entity;

import java.util.Date;

public class GradeDistribution {
    private String curdate;

    private Integer curgrade;

    private Integer curexp;

    private Integer curusers;

    private Date curtime;

    private Integer countyCode;

    public String getCurdate() {
        return curdate;
    }

    public void setCurdate(String curdate) {
        this.curdate = curdate;
    }

    public Integer getCurgrade() {
        return curgrade;
    }

    public void setCurgrade(Integer curgrade) {
        this.curgrade = curgrade;
    }

    public Integer getCurexp() {
        return curexp;
    }

    public void setCurexp(Integer curexp) {
        this.curexp = curexp;
    }

    public Integer getCurusers() {
        return curusers;
    }

    public void setCurusers(Integer curusers) {
        this.curusers = curusers;
    }

    public Date getCurtime() {
        return curtime;
    }

    public void setCurtime(Date curtime) {
        this.curtime = curtime;
    }

    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }
}