package com.zcl.redisTest;

public class TmpTest{
	
	public static void main(String[] args) {
		Runnable runnable = () ->System.out.println("lamda表达式");
		new Thread(runnable).start();
		
		Thread aa = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		aa.start();
		Runnable runnable_ = TmpTest::tell;
		new Thread(runnable_).start(); 
		Runnable runnable__ = new TmpTest()::tell_;
		
		new Thread(runnable__).start(); 
	}
	public static void tell(){
		System.out.println("1方法引用的代码。。。");
	}
	public void tell_(){
		System.out.println("2方法引用的代码。。。");
	}
	
}
