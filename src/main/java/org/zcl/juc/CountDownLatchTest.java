package org.zcl.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	static CountDownLatch c = new CountDownLatch(3);

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				c.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				int sum = 0;
				for(int i=0;i<1000000000;i++){
					sum += i;
				}
				System.out.println("2>>>>>>>>>>>>>>>>"+sum);
				c.countDown();
			}
		}).start();
		c.countDown();
//		c.await(1, TimeUnit.MILLISECONDS);
		c.await();
		System.out.println("3");
	}

}