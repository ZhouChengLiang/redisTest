package org.zcl.juc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TestReentrantLock {
	
	private static ReentrantLock_copy fairLock = new ReentrantLock_copy(true);
	
	private static ReentrantLock_copy unfairLock = new ReentrantLock_copy(false);
	
	private static class ReentrantLock_copy extends ReentrantLock{
		
		public ReentrantLock_copy(boolean fair){
			super(fair);
		}
		
		public Collection<Thread> getQueuedThreads() {
			List<Thread> list = new ArrayList<>(super.getQueuedThreads());
			Collections.reverse(list);
			return list;
		}
	}
	
	private static class MyThread extends Thread{
		private ReentrantLock_copy lock;
		
		public MyThread(ReentrantLock_copy lock){
			this.lock = lock;
		}
		
		@Override
		public void run() {
			for(int i = 0;i<2;i++){
				lock.lock();
				try {
					System.out.println(Thread.currentThread().getName()+" get the lock and "+lock.getQueuedThreads().stream().map(t -> t.getName()).collect(Collectors.toList())+" are waiting...");
				} finally {
					lock.unlock();
				}
			}
			
		}
	}

	private static void testLock(ReentrantLock_copy lock) {
		MyThread my = new MyThread(lock);
		new Thread(my,"A0").start();
		new Thread(my,"A1").start();
		new Thread(my,"A2").start();
		new Thread(my,"A3").start();
		new Thread(my,"A4").start();
		new Thread(my,"A5").start();
	}
	
	public static void main(String[] args) {
//		testLock(unfairLock);
		testLock(fairLock);
	}
}
