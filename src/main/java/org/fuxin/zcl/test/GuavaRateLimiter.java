package org.fuxin.zcl.test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimiter {
	final RateLimiter limit = RateLimiter.create(100);//10ms就有一个令牌
	
	public void handlerWithLimiter(){
		boolean hasToken = limit.tryAcquire(5, TimeUnit.MILLISECONDS);//尝试获得令牌,允许最大等待时间5ms内获得令牌
		if(hasToken){
			System.out.println(Thread.currentThread().getName()+" has token to do something...");
		}else{
			System.out.println(Thread.currentThread().getName()+" has not get the Token!!!!");
		}
	}
	
	public static void main(String[] args) throws Exception {
		GuavaRateLimiter gr = new GuavaRateLimiter();
		Instant start = Instant.now();
		Thread t1 = new Thread(()->gr.handlerWithLimiter(), "A01");
		t1.start();
		Thread t2 = new Thread(()->gr.handlerWithLimiter(), "A02");
		t2.start();
		Thread t3 = new Thread(()->gr.handlerWithLimiter(), "A03");
		t3.start();
		Thread t4 = new Thread(()->gr.handlerWithLimiter(), "A04");
		t4.start();
		Thread t5 = new Thread(()->gr.handlerWithLimiter(), "A05");
		t5.start();
		Thread t6 = new Thread(()->gr.handlerWithLimiter(), "A06");
		t6.start();
		Thread t7 = new Thread(()->gr.handlerWithLimiter(), "A07");
		t7.start();
		Thread t8 = new Thread(()->gr.handlerWithLimiter(), "A08");
		t8.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
		t8.join();
		System.out.println("It costs "+Duration.between(start, Instant.now()));
	}
}
