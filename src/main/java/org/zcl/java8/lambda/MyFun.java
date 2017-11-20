package org.zcl.java8.lambda;

public interface MyFun {
	
	default String getName(){
		return "哈哈哈";
	}
	public Integer getValue(Integer num);
}
