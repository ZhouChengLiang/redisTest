package org.zcl.mytest.juhe;

public class SignInfoInner {
	
	private String avoid;
	
	private String animalsYear;
	
	private String weekday;
	
	private String suit;
	
	private String lunarYear;
	
	private String date;
	
	private String holiday;
	public String getAvoid() {
		return avoid;
	}

	public void setAvoid(String avoid) {
		this.avoid = avoid;
	}

	public String getAnimalsYear() {
		return animalsYear;
	}

	public void setAnimalsYear(String animalsYear) {
		this.animalsYear = animalsYear;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getLunarYear() {
		return lunarYear;
	}

	public void setLunarYear(String lunarYear) {
		this.lunarYear = lunarYear;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SignInfoInner [avoid=" + avoid + ", animalsYear=" + animalsYear + ", weekday=" + weekday + ", suit="
				+ suit + ", lunarYear=" + lunarYear + ", date=" + date + "]";
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	

}
