package org.zcl.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	final Semaphore s = new Semaphore(2,true);
	
	public void dosomethings(){
		try {
			s.acquire();
			System.out.println(Thread.currentThread().getName()+" >>> "+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			s.release();
		}
	}
	public static void main(String[] args) {
		SemaphoreTest st = new SemaphoreTest();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		new Thread(()->st.dosomethings()).start();
		
	}
}