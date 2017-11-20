package org.zcl.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.ReadWriteLock :读写锁
 * 
 * 写写/读写 需要“互斥”
 * 读读 不需要互斥
 * @author Administrator
 *
 */
public class TestReadWriteLock {
	public static void main(String[] args) {
		ReadWriteLockDemo rw = new ReadWriteLockDemo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				rw.set((int) (Math.random()*101));
			}
		},"Write").start();
		
		for(int i = 0;i<10;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					rw.get();
				}
			},"Read").start();
		}
	}
}
class ReadWriteLockDemo{
	private int number = 0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	//读
	public void get(){
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+" : "+number);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	//写
	public void set(int number){
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName());
			this.number = number;
		} finally {
			lock.writeLock().unlock();
		}
	}
	
}