package org.zcl.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * CountDownLatch 闭锁 在完成某些运算时,只有其他线程的运算全部完成,当前运算才继续执行
 * @author Administrator
 *
 */
public class TestCountDownLatchAndCyclicBarrier {
	
	public static void main(String[] args) {
		
		CountDownLatch cdl = new CountDownLatch(3);
		TwoUseDemo ldtest = new TwoUseDemo(cdl);
		Instant start_0 = Instant.now();
		for(int i = 0;i<3;i++){
			new Thread(()->ldtest.testCountDownLatch(),"ldtest_"+i).start();
		}
		try {
			cdl.await();
			System.out.println(Thread.currentThread().getName()+" 耗费时间为>>>>>>>>"+Duration.between(start_0, Instant.now()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Instant start_1 = Instant.now();
		CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" 耗费时间>>>>>>>>"+Duration.between(start_1, Instant.now()));
			}
		});
		TwoUseDemo cbtest = new TwoUseDemo(cb);
		for(int i = 0;i<3;i++){
			new Thread(()->cbtest.testCyclicBarrier(),"cbtest_"+i).start();
		}
		
		TwoUseDemo blankTest = new TwoUseDemo();
		Instant start_2 = Instant.now();
		List<Thread> threads = new ArrayList<>();
		
		IntStream.range(0, 3).forEach((i)->{
			threads.add(new Thread(()->{
				blankTest.blank();
			},"blank_"+i));
		});
		threads.stream().forEach((t)->{
			t.start();
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		System.out.println(Thread.currentThread().getName()+" 耗费时间为>>>>>>>>"+Duration.between(start_2, Instant.now()));
	}
}

class TwoUseDemo{
	
	private CountDownLatch latch;
	
	private CyclicBarrier cb;
	
	public TwoUseDemo(){
		
	}
	
	public TwoUseDemo(CountDownLatch latch){
		this.latch = latch;
	}
	
	public TwoUseDemo(CyclicBarrier cb){
		this.cb = cb;
	}
	
	public void testCountDownLatch(){
		for(int i = 0;i<6;i++){
			if(i%2==0){
				System.out.println(Thread.currentThread().getName()+">>>>>>>>>>>>>>>"+i);
			}
		}
		latch.countDown();
	}
	
	public void testCyclicBarrier(){
		for(int i = 0;i<6;i++){
			if(i%2==0){
				System.out.println(Thread.currentThread().getName()+">>>>>>>>>>>>>>>"+i);
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
	
	public void blank(){
		for(int i = 0;i<6;i++){
			if(i%2==0){
				System.out.println(Thread.currentThread().getName()+">>>>>>>>>>>>>>>"+i);
			}
		}
	}
}
