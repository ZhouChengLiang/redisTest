package org.zcl.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、用于解决多线程安全的方式
 * synchronized 隐式锁
 * 1.同步代码块
 * 
 * 2.同步方法
 * 
 * jdk 1.5后
 * 3.同步锁  Lock
 * 注意:是一个显示锁 需要通过 lock()方法上锁,必须通过unlock()方法进行解锁
 * @author Administrator
 *
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket tk = new Ticket();
		new Thread(tk,"1号窗口").start();
		new Thread(tk,"2号窗口").start();
		new Thread(tk,"3号窗口").start();
	}
}
class Ticket implements Runnable{
	private int tick = 100;
	
	private Lock lock = new ReentrantLock();
	@Override
	public void run() {
		while(true){
			lock.lock();
			try{
				if(tick>0){
					try {
						Thread.sleep(200);
						System.out.println(Thread.currentThread().getName()+" 完成售票，余票为:"+--tick);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}finally{
				lock.unlock();
			}
			
		}
	}
	
}
