package com.zcl.redisTest.impl;

import com.zcl.redisTest.InterfaceInJava8;

public class SpecificInterface implements InterfaceInJava8 {
	@Override
	public void print() {
		InterfaceInJava8.super.print();
		System.out.println("9876543210");
	}
	public static void main(String[] args) {
		InterfaceInJava8 i8 = new SpecificInterface();
		i8.print();
	}
}
