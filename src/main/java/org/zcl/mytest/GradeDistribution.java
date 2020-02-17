package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeDistribution {
    private String curdate;

    private Integer curgrade;

    private Integer curexp;

    private Integer curusers;

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

	public Integer getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}


}