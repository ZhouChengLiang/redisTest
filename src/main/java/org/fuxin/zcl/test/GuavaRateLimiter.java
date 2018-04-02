package org.fuxin.zcl.test;

import java.util.UUID;
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
	
	
	public void limit(){
		/* 每秒向桶中存放5个Token */
		for(int i = 0;i< 10;i++){
			double waitTime = limit.acquire();
			System.out.println(waitTime+">>>>>>"+UUID.randomUUID().toString());
		}
	}
	
	
	public void tryDoSomeThings(){
		/*boolean result = limit.tryAcquire();
		
		if(result){
			System.out.println(Thread.currentThread().getName()+" 成功获取到Token");
		}*/
		
		//尝试从桶中获取Token，只等待50ms
		boolean result = limit.tryAcquire(50, TimeUnit.MILLISECONDS);
		if(result){
			System.out.println(Thread.currentThread().getName()+" 等待时间后成功获取到Token");
		}else{
			System.out.println(Thread.currentThread().getName()+" 等待时间后还是没能获取到Token！！！");
		}
//		double waitTime = limit.acquire();
//		System.out.println(Thread.currentThread().getName()+" 成功获取到Token");
	}
	public static void main(String[] args) {
		System.out.println();
	}
}
