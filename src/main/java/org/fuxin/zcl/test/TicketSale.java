package org.fuxin.zcl.test;

public class TicketSale implements Runnable{
	private int tickets = 10;

	@Override
	synchronized public void run() {
		if (tickets>0){
			tickets--;
			System.out.println(Thread.currentThread().getName() +" 获得 这一张票。。。");
		}else{
			System.out.println("票已售罄....");
		}
	}
	
	public static void main(String[] args) {
		/*TicketSale ts = new TicketSale();
		new Thread(ts,"A1").start();
		new Thread(ts,"B1").start();
		new Thread(ts,"C1").start();
		new Thread(ts,"D1").start();
		new Thread(ts,"E1").start();
		new Thread(ts,"F1").start();
		new Thread(ts,"G1").start();
		new Thread(ts,"A2").start();
		new Thread(ts,"B2").start();
		new Thread(ts,"C2").start();
		new Thread(ts,"D2").start();
		new Thread(ts,"E2").start();
		new Thread(ts,"F2").start();
		new Thread(ts,"G2").start();*/
		
	}
	
}
