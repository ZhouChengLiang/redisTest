package org.zcl.mytest.juhe;

/**
 * "avoid":"作灶.安葬.祭祀.开市.纳采.订盟.纳畜.谢土.出行.探病.",
			"animalsYear":"鸡",
			"weekday":"星期三",
			"suit":"嫁娶.开光.解除.出火.拆卸.修造.进人口.入宅.移徙.安床.栽种.入殓.修坟.动土.除服.成服.",
			"lunarYear":"丁酉年",
			"lunar":"十一月廿四",
			"year-month":"2018-1",
			"date":"2018-1-10"
 * @author Administrator
 *
 */
public class SignInfo {
	private SignInfoInner data;

	public SignInfoInner getData() {
		return data;
	}

	public void setData(SignInfoInner data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SignInfo [data=" + data + "]";
	}
	
}
