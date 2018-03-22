package org.zcl.juc;

public class VolatileInc implements Runnable {
	private static volatile int count = 0; // 使用 volatile 修饰基本数据内存不能保证原子性
//	 private static AtomicInteger count = new AtomicInteger() ;

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			count++;
//			 count.incrementAndGet() ;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		VolatileInc volatileInc = new VolatileInc();
		Thread t1 = new Thread(volatileInc, "t1");
		Thread t2 = new Thread(volatileInc, "t2");
		t1.start();
		t1.join();  //此处取消注释 相当于让线程逐个 串行执行程序 
		t2.start();
		t2.join();	//此处取消注释 相当于让线程逐个 串行执行程序 
		for (int i = 0; i < 10000; i++) {
			count++;
//			 count.incrementAndGet();
		}
		System.out.println("最终Count = " + count);
	}
}