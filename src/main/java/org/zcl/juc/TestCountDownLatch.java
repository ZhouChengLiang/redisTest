package org.zcl.juc;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 闭锁 在完成某些运算时,只有其他线程的运算全部完成,当前运算才继续执行
 * @author Administrator
 *
 */
public class TestCountDownLatch {
	
	public static void main(String[] args) {
		
		CountDownLatch cdl = new CountDownLatch(5);
		
		LatchDemo ld = new LatchDemo(cdl);
		
		long start = Instant.now().toEpochMilli();
		for(int i = 0;i<5;i++){
			new Thread(ld).start();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = Instant.now().toEpochMilli();
		System.out.println("耗费时间为: "+(end-start));
	}
}

class LatchDemo implements Runnable{
	
	private CountDownLatch latch;
	
	public LatchDemo(CountDownLatch latch){
		this.latch = latch;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			try {
				for(int i = 0;i<10;i++){
					if(i%2==0){
						System.out.println(i);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				latch.countDown();
			}
		}
		
		
		
	}
}
