package org.fuxin.zcl.test;

import org.junit.Test;

public class AboutCatchTwice {
	
	@Test
	public void test0(){
		
		try {
			String a = null;
			System.out.println(a.toCharArray());
		} catch (NullPointerException e) {
			System.out.println("111111111111111111111");
		} catch(Exception e){
			System.out.println("222222222222222222222");
		}
	}
}
