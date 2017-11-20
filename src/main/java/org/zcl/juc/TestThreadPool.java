package org.zcl.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池:提供了一个线程队列,队列中保存着所有等待状态的线程,避免了创建与销毁额外开销
 * 提高了响应的速度
 * 
 * 一、线程池的体系结构
 * java.util.concurrent.Executor:负责线程的使用于调度的根接口
 * 		|--ExecutorServer 子接口: 线程池的主要接口
 * 			|--ThreadPoolExecutor 线程池实现类
 * 			|--ScheduledExecutorService 子接口:负责线程的调度
 * 				|--	ScheduledThreadPoolExecutor 继承ThreadPoolExecutor 实现了ScheduledExecutorService
 * @author Administrator
 *
 *二、工具类:Executors
 *newFixedThreadPool() 创建规定大小的线程池
 *newCachedThreadPool() 缓存线程池,线程池的数量不固定,可以根据需求自动的更改数量
 *newSingleThreadExecutor() 创建单个线程池,线程池中只有一个线程
 *
 *ScheduledExecutorService  new ScheduledThreadPoolExecutor 创建固定大小的县城,可以延迟或定时的执行任务
 *
 */
public class TestThreadPool {
	public static void main(String[] args) throws Exception {
		ThreadPoolDemo tpd = new ThreadPoolDemo();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		List<Future<Integer>> list = new ArrayList<>();
		for(int i = 0;i<10;i++){
			Future<Integer> future = pool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int sum = 0;
					for(int i = 0;i < 100;i++){
						sum += i;
					}
					return sum;
				}
			});
			list.add(future);
		}
		pool.shutdown();
	}
//	new Thread(tpd).start();
//	new Thread(tpd).start();
	/*// 1.创建线程池
			ExecutorService pool = Executors.newFixedThreadPool(5);
			
			// 2.为线程池中的县城分配任务
			for(int i = 0;i<10;i++){
				pool.submit(tpd);
			}
			
			// 3.关闭线程池
			pool.shutdown();*/
}
class ThreadPoolDemo implements Runnable{
	private int i = 0;
	@Override
	public void run() {
		while(i <= 10){
			System.out.println(Thread.currentThread().getName()+" : "+i++);
		}
		
	}
}
