package org.zcl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABAlternate2 {
	public static void main(String[] args) {
		AlternateDemo1 ad = new AlternateDemo1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 30; i++) {
					ad.loopA(i);
				}
			}
		},"A").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <=30; i++) {
					ad.loopB(i);
				}
			}
		},"B").start();
	}
}
class AlternateDemo1{
	private int number = 1;//当前正在执行线程的标记
	
	private Lock lock = new ReentrantLock();
	;
	private Condition condition1 = lock.newCondition();
	
//	private Condition condition2 = lock.newCondition();//没必要用两个吧？
	
	
	/**
	 * 
	 * @param totalLoop
	 */
	public void loopA(int totalLoop){
		lock.lock();
		try {
			if(number != 1){
				condition1.await();
			}
			for(int i = 0;i<1;i++){
				System.out.println(Thread.currentThread().getName()+" \t"+i+"\t "+totalLoop);
			}
			number = 2;
			condition1.signal();
		} catch (Exception e) {
			
		}finally{
			lock.unlock();
		}
	}
	
	public void loopB(int totalLoop){
		lock.lock();
		try {
			//
			if(number != 2){
				condition1.await();
			}
			for(int i = 0;i<1;i++){
				System.out.println(Thread.currentThread().getName()+" \t"+i+"\t "+totalLoop);
			}
			System.out.println("***********************************************************");
			number = 1;
			condition1.signal();
		} catch (Exception e) {
			
		}finally{
			lock.unlock();
		}
	}
}
