package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
