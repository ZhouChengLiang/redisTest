package org.zcl.mytest;

public class SimpleTest {
	public static void main(String[] args) {
		int a = 1;
//		System.out.println(a|a);
//		System.out.println(a^a);
//		System.out.println(a&a);
//		System.out.println((a^a)|(a&a));
//		System.out.println((a|a)&(a^a));
//		System.out.println("****************************");
		int b = 0;
		System.out.println(b|b);
		System.out.println(b^b);
		System.out.println(b&b);
		System.out.println((b^b)|(b&b));
		System.out.println((b|b)&(b^b));
		System.out.println((b|b)^(b&b));
		System.out.println((b|b)^(b|b));
		System.out.println((b&b)^(b&b));
		System.out.println((b^b)^(b^b));
	}
}
