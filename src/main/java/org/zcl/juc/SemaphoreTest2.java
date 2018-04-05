package org.zcl.juc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SemaphoreTest2 {

	final Semaphore_copy sc = new Semaphore_copy(2,true);
	
	private static class Semaphore_copy extends Semaphore{
		
		public Semaphore_copy(int permits,boolean fair){
			super(permits, fair);
		}
		
		@Override
		protected Collection<Thread> getQueuedThreads() {
			List<Thread> list = new ArrayList<>(super.getQueuedThreads());
			Collections.reverse(list);
			return list;
		}
		
	}
	
	public void dosomethings(){
		try {
			sc.acquire();
			System.out.println(Thread.currentThread().getName()+" get the Semaphore>>> "+sc.getQueuedThreads().stream().map(t -> t.getName()).collect(Collectors.toList())+" are waiting...");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			sc.release();
		}
	}
	public static void main(String[] args) {
		SemaphoreTest2 st = new SemaphoreTest2();
		ThreadPoolExecutor pool  = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20));
		for(int i = 0;i<20;i++){
			pool.execute(new Thread(()->st.dosomethings()));
		}
		pool.shutdown();
		
	}
}