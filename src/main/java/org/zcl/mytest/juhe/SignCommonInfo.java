package org.zcl.mytest.juhe;

/**
 * {
	"reason":"Success",
	"result":{
		"data":{
			"avoid":"作灶.安葬.祭祀.开市.纳采.订盟.纳畜.谢土.出行.探病.",
			"animalsYear":"鸡",
			"weekday":"星期三",
			"suit":"嫁娶.开光.解除.出火.拆卸.修造.进人口.入宅.移徙.安床.栽种.入殓.修坟.动土.除服.成服.",
			"lunarYear":"丁酉年",
			"lunar":"十一月廿四",
			"year-month":"2018-1",
			"date":"2018-1-10"
		}
	},
	"error_code":0
}
 * @author Administrator
 *
 */
public class SignCommonInfo {
	
	private String reason;
	
	private Integer error_code;
	
	private SignInfo result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public SignInfo getResult() {
		return result;
	}

	public void setResult(SignInfo result) {
		this.result = result;
	}

	
	
}
