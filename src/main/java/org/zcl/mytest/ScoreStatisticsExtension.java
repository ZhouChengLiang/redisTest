package org.zcl.mytest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreStatisticsExtension {
	private String date;

	private Long grantScores;

	private Long signedScores;

	private Long growthScores;

	private Long dailyScores;

	private Long bonusScores;

	private Long consumedScores;

	private Long busiScores;

	private Long deducteScores;

	private Integer countyCode;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getGrantScores() {
		return grantScores;
	}

	public void setGrantScores(Long grantScores) {
		this.grantScores = grantScores;
	}

	public Long getSignedScores() {
		return signedScores;
	}

	public void setSignedScores(Long signedScores) {
		this.signedScores = signedScores;
	}

	public Long getGrowthScores() {
		return growthScores;
	}

	public void setGrowthScores(Long growthScores) {
		this.growthScores = growthScores;
	}

	public Long getDailyScores() {
		return dailyScores;
	}

	public void setDailyScores(Long dailyScores) {
		this.dailyScores = dailyScores;
	}

	public Long getBonusScores() {
		return bonusScores;
	}

	public void setBonusScores(Long bonusScores) {
		this.bonusScores = bonusScores;
	}

	public Long getConsumedScores() {
		return consumedScores;
	}

	public void setConsumedScores(Long consumedScores) {
		this.consumedScores = consumedScores;
	}

	public Long getBusiScores() {
		return busiScores;
	}

	public void setBusiScores(Long busiScores) {
		this.busiScores = busiScores;
	}

	public Long getDeducteScores() {
		return deducteScores;
	}

	public void setDeducteScores(Long deducteScores) {
		this.deducteScores = deducteScores;
	}

	public Integer getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}

	public ScoreStatisticsExtension(String date, Long grantScores, Long signedScores, Long growthScores,
			Long dailyScores, Long bonusScores, Long consumedScores, Long busiScores, Long deducteScores,
			Integer countyCode) {
		super();
		this.date = date;
		this.grantScores = grantScores;
		this.signedScores = signedScores;
		this.growthScores = growthScores;
		this.dailyScores = dailyScores;
		this.bonusScores = bonusScores;
		this.consumedScores = consumedScores;
		this.busiScores = busiScores;
		this.deducteScores = deducteScores;
		this.countyCode = countyCode;
	}
	
	public ScoreStatisticsExtension(){}
	
}
