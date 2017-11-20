package org.zcl.juc;

//1.两个普通不同方法，两个线程,标准打印, //one two
//2.新增 Thread.sleep()给 getOne(),//one two
//3.新增普通方法 getThree() 
//4.两个普通不同方法,两个Number对象
//5.修改getOne()为静态同步方法
//6.修改两个方法均为静态不同方法,一个Number对象
//7.一个静态同步方法,一个非静态同步方法,两个Number 对象
//8.两个静态同步方法,两个Number对象
//
//线程八锁的关键:
//非静态方法的锁默认为this,静态方法的锁为对应的Class实例
//某一个时刻内,只能有一个线程持有锁,无论几个方法
public class TestThread8Monitor {

	public static void main(String[] args) {
		Number number = new Number();
//		Number number2 = new Number();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					number.getOne();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getTwo();
//				number2.getTwo();
			}
		}).start();
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				number.getThree();
			}
		}).start();*/
	}
}
class Number{
	public static synchronized void getOne(){//Number.class
		System.out.println("one");
	}
	
	public synchronized void getTwo(){//this
		System.out.println("two");
	}
	public void getThree(){
		System.out.println("three");
	}
}