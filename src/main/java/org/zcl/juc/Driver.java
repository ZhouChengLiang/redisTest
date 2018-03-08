package org.zcl.juc;

import java.util.concurrent.CountDownLatch;

public class Driver {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(5);
		for (int i = 0; i < 5; ++i) {
			new Thread(new Worker(startSignal, doneSignal)).start();
			System.out.println("工人 "+i+" 等待司机到位再工作了 。。。。");
		}
		System.out.println("startSignal.getCount()>>>>>>>>"+startSignal.getCount());
		System.out.println("doneSignal.getCount()>>>>>>>>"+doneSignal.getCount());
		startSignal.countDown();
		System.out.println("startSignal.getCount()>>>>>>>>"+startSignal.getCount());
		
		doneSignal.await(); 
		System.out.println("~~~~~~~~~~~~~");
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
			System.out.println("doneSignal.getCount()>>>>>>>>"+doneSignal.getCount());
		} catch (InterruptedException ex) {
		} 
	}

	private void doWork() {
		System.out.println("工人随便做点事~~~~");
	}

}