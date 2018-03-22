package org.zcl.mytest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class PrintStrStyleTest {
	public static void main(String[] args) {
		/*String str = "";
		for(int i = 0;i<10;i++){
			str += UUID.randomUUID().toString();
		}
		System.out.println("str>>>>>>>>>>>>>>>>>>>>>>>"+str);
		System.out.println("***************************************");
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<10;i++){
			sb.append(UUID.randomUUID().toString());
		}
		System.out.println("sb>>>>>>>>>>>>>>>>>>>>>>>>"+sb.toString());
		String str1 = "abc";
		String str2 = "abc";
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));*/
	}
	
	@Test
	public void test0(){
		List<String> list = new ArrayList<>();
		for(;;){
			list.add(UUID.randomUUID().toString());
		}
	}
	
	@Test
	public void test1() {
		
	}
}
