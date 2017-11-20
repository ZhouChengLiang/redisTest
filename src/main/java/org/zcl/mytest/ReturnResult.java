package org.zcl.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.gexin.fastjson.JSON;

public class ReturnResult {
	private String date;
	private Collection<Integer> list = new ArrayList<Integer>();
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Collection<Integer> getList() {
		return list;
	}
	public void setList(Collection<Integer> list) {
		this.list = list;
	}
	
	public ReturnResult(){
		
	}
	
	public ReturnResult(String date,List<Integer> list){
		this.date = date;
		this.list = list;
	}
	
	public static void main(String[] args) {
		List<ReturnResult> rrList = new ArrayList<ReturnResult>();
		ReturnResult rr1 = new ReturnResult("20171027",Arrays.asList(1,2,3,4,5,6));
		ReturnResult rr2 = new ReturnResult("20171028",Arrays.asList(7,8,9,10,11,12));
		ReturnResult rr3 = new ReturnResult("20171029",Arrays.asList(13,14,15,16,17,18));
		rrList.add(rr1);
		rrList.add(rr2);
		rrList.add(rr3);
		System.out.println(JSON.toJSON(rrList));
	}
}
