package com.zcl.redisTest;

import org.junit.Test;
import org.zcl.mytest.ConstellatoryEnum;

public class ConstellatoryEnumTest {
	
	@Test
	public void test0(){
		for(ConstellatoryEnum constellatoryEnum:ConstellatoryEnum.values()){
			/*
			System.out.println(">>>>>>>1<<<<<<<"+constellatoryEnum.getName());//水瓶座
			System.out.println(">>>>>>>2<<<<<<<"+constellatoryEnum.name());//AQUARIUS
			System.out.println(">>>>>>>3<<<<<<<"+constellatoryEnum.getCode());//11
			*/
			/*System.out.println(ConstellatoryEnum.valueOf("ARIES").getCode());
			System.out.println(ConstellatoryEnum.valueOf("ARIES").getName());*/
		}
	}
	
	@Test
	public void test1(){
		ConstellatoryEnum c1 = ConstellatoryEnum.codeOf(1);
		ConstellatoryEnum c2 = ConstellatoryEnum.nameOf("白羊座");
		System.out.println("c1>>>>>>>>>>>>>>>>>>"+c1);
		System.out.println("c2>>>>>>>>>>>>>>>>>>"+c2);
		System.out.println(c1.equals(c2));
		
	}
}
