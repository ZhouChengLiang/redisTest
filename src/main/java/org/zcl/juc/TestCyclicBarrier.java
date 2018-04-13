package org.zcl.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.joda.time.Instant;

public class TestCyclicBarrier {
	public static void main(String[] args) {
		long start = Instant.now().getMillis();
		CyclicBarrier cb = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				long end = Instant.now().getMillis();
				System.out.println("耗费时间>>>>>>>>"+(end-start));
			}
		});
		ExecutorService executor = Executors.newFixedThreadPool(5);
//		System.out.println("CPU Counts>>>>>>>>>>>>>>>"+Runtime.getRuntime().availableProcessors());
		executor.execute(new PrintNum(cb));
		executor.execute(new PrintNum(cb));
		executor.execute(new PrintNum(cb));
		executor.execute(new PrintNum(cb));
		executor.execute(new PrintNum(cb));
		executor.shutdown();
	}
}
class PrintNum implements Runnable{
	private CyclicBarrier cb;
	
	public PrintNum(CyclicBarrier cb){
		this.cb = cb;
	}
	
	@Override
	public void run() {

		for(int i = 0;i<10;i++){
			if(i%2==0){
				System.out.println(Thread.currentThread().getName()+" >>>>>> "+i);
			}
		}
		try {
			cb.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	
		
	}
}
