package org.fuxin.zcl.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSaleTest{
	
	public static void main(String[] args) throws Exception {
		Station st = new Station();
		
		Person p1 = new Person(st);
		Person p2 = new Person(st);
		Person p3 = new Person(st);
		Person p4 = new Person(st);
		Person p5 = new Person(st);
		
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		Thread t3 = new Thread(p3);
		Thread t4 = new Thread(p4);
		Thread t5 = new Thread(p5);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

	
	
}

/**
 * 车站
 * @author Administrator
 *
 */
class Station{
	private int tickets = 1000;
	
	private Lock lock = new ReentrantLock();
	
	public boolean saleTick(){
		lock.lock();
		try {
			if(tickets>0){
				tickets--;
				return true;
			}
		} finally {
			lock.unlock();
		}
		return false;
	}
}

/**
 * 购票人
 * @author Administrator
 *
 */
class Person implements Runnable{
	private Station station;
	
	public Person(Station station){
		this.station = station;
	}

	@Override
	public void run() {
		int ownCount = 0;
		for(int i = 0;i<300;i++){
			if(station.saleTick()){
				ownCount++;
			}else{
				break;
			}
		}
		System.out.println(Thread.currentThread().getName()+" 买到了 "+ownCount+" 张票");
	}

}