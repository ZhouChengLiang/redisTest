package org.zcl.mytest;

import org.junit.Test;

public enum SourceEnum {
	ZB("浙报"),
	WX("微信"),
	QQ("QQ");
	
    private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	SourceEnum(String name){
		this.name = name;
	}
	
	public static void main(String[] args) {
		
	}
}