package org.zcl.juc;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier2 {
	public static void main(String[] args) throws Exception, BrokenBarrierException {
		long start=1L;//开始值
        long end=100000000L;//结束值
        int parties = 5;
		Vector<Long> v= new Vector<>();
		/*CyclicBarrier cb = new CyclicBarrier(parties, new Runnable() {
			@Override
			public void run() {
				System.out.println("totalSum>>>>>>>>>>>>"+v.stream().reduce(0L, Long::sum));
			}
		});*/
		CyclicBarrier cb = new CyclicBarrier(parties);
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0;i<parties;i++){
			executor.submit(new CalculteSum(cb,v,start,end,parties,i));
		}
		System.out.println("totalSum>>>>>>>>>>>>"+v.stream().reduce(0L, Long::sum));
		executor.shutdown();
	}
	static class CalculteSum implements Runnable{
		private CyclicBarrier cb;
		private Vector<Long> v;
		private long start;
	    private long end;
		public CalculteSum(CyclicBarrier cb,Vector<Long> v,long start, long end, int parties, int index){
			this.cb = cb;
			this.v = v;
			this.start = index * (end-start)/parties;
	        this.end = (index+1) * (end-start)/parties-1;
	        if(index==0){
	            this.start = start;
	        }
	        if(index == parties-1){
	            this.end = end;
	        }
		}
		
		@Override
		public void run() {
			long partSum = 0;
			for(long i=start;i<=end;i++){
				partSum +=i;
	        }
			v.addElement(partSum);
			try {
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}

