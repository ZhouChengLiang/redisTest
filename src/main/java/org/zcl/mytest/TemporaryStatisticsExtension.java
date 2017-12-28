package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryStatisticsExtension {
	
	private String date;
	
	private String event;
	
	private Integer eventType;
	
	private Long partScore;
	
	private Integer countyCode;
}
