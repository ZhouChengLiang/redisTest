package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserExpIntExtra {
    private Integer userId;

    private Integer experience;

    private Integer integral;
    
    private Integer countyCode;
    
    private GradeInfo gradeInfo;
    
    private Integer beyondOthers;
    
    private String doubleRank;
	@Override
	public String toString() {
		return "user [" + userId + "," + countyCode + "," + gradeInfo.getGrade()+"," + gradeInfo.getGradeName()+","+doubleRank+","+beyondOthers+"]";
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Integer getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}
	public GradeInfo getGradeInfo() {
		return gradeInfo;
	}
	public void setGradeInfo(GradeInfo gradeInfo) {
		this.gradeInfo = gradeInfo;
	}
	public Integer getBeyondOthers() {
		return beyondOthers;
	}
	public void setBeyondOthers(Integer beyondOthers) {
		this.beyondOthers = beyondOthers;
	}
	public String getDoubleRank() {
		return doubleRank;
	}
	public void setDoubleRank(String doubleRank) {
		this.doubleRank = doubleRank;
	}
	public UserExpIntExtra(Integer userId, Integer experience, Integer integral, Integer countyCode,
			GradeInfo gradeInfo, Integer beyondOthers, String doubleRank) {
		super();
		this.userId = userId;
		this.experience = experience;
		this.integral = integral;
		this.countyCode = countyCode;
		this.gradeInfo = gradeInfo;
		this.beyondOthers = beyondOthers;
		this.doubleRank = doubleRank;
	}
    
	public UserExpIntExtra(){}
    

}