package org.zcl.juc;
public class JoinCountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		Thread parser1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser1 finish");
			}
		});

		Thread parser2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser2 finish");
			}
		});
		
		Thread parser3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser3 finish");
			}
		});

		parser1.start();
		parser2.start();
		parser3.start();
		parser1.join();
		parser2.join();
		parser3.join();
		System.out.println("all parser finish");
	}

}