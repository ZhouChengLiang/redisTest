package org.zcl.mytest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemporaryStatisticsExtension {
	
	private String date;
	
	private String event;
	
	private Integer eventType;
	
	private Long partScore;
	
	private Integer countyCode;

	public TemporaryStatisticsExtension(String date, String event, Integer eventType, Long partScore,
			Integer countyCode) {
		super();
		this.date = date;
		this.event = event;
		this.eventType = eventType;
		this.partScore = partScore;
		this.countyCode = countyCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Long getPartScore() {
		return partScore;
	}

	public void setPartScore(Long partScore) {
		this.partScore = partScore;
	}

	public Integer getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}
	
	
}
