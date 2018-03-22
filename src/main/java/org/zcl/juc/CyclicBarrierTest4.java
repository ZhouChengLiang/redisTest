package org.zcl.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest4 {


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
    	cyclicBarrierLock();
//    	countDownLatchLock();
    }
    
    private static void cyclicBarrierLock(){
    	CyclicBarrier c = new CyclicBarrier(2);
        try {
            c.await();
            System.out.println(">>>>>>>>>>>>>cyclicBarrierLock<<<<<<<<<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void countDownLatchLock(){
    	CountDownLatch c = new CountDownLatch(3);
    	System.out.println("c.getCount()>>>>>>>"+c.getCount());
    	c.countDown();
    	System.out.println("c.getCount()>>>>>>>"+c.getCount());
    	c.countDown();
    	System.out.println("c.getCount()>>>>>>>"+c.getCount());
    	c.countDown();
    	System.out.println("c.getCount()>>>>>>>"+c.getCount());
    	c.countDown();
    	System.out.println("c.getCount()>>>>>>>"+c.getCount());
    	c.countDown();
    	System.out.println("c.getCount()>>>>>>>"+c.getCount());
    	try {
			c.await();
			System.out.println(">>>>>>>>>>>>>countDownLatchLock<<<<<<<<<<<<<");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}