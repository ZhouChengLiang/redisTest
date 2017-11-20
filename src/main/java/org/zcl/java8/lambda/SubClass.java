package org.zcl.java8.lambda;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface{

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

	@Override
	public Integer getValue(Integer num) {
		// TODO Auto-generated method stub
		return null;
	}

}
