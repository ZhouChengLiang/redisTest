package org.zcl.mytest;

import java.util.Optional;

import org.junit.Test;

public class TestSourceEnum {
	@Test
	public void test0(){
		System.out.println(SourceEnum.valueOf("ZB").getName());
		String tmp = "ZB:123";
		tmp = tmp.substring(0, tmp.indexOf(":"));
		System.out.println(tmp);
	}
	
	@Test
	public void test1(){
		Optional<SourceEnum> sourceEnum =
					Optional.ofNullable(SourceEnum.valueOf(""));
		//System.out.println(sourceEnum.get().getName());
	}
	@Test
	public void test2(){
		
	}
	
	public String getSourceEnum(Optional<SourceEnum> sourceEnum){
		return sourceEnum.orElse(SourceEnum.ZB).getName();
	}
}
