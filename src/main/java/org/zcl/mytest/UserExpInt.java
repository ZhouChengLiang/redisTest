package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserExpInt {
    private Integer userId;

    private Integer experience;

    private Integer integral;
    
    private Integer countyCode;

	@Override
	public String toString() {
		return "user [" + userId + "," + countyCode + "]";
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

	public UserExpInt(Integer userId, Integer experience, Integer integral, Integer countyCode) {
		this.userId = userId;
		this.experience = experience;
		this.integral = integral;
		this.countyCode = countyCode;
	}
    
	public UserExpInt(){}
    

}