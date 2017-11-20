public class TestWithVolatile4 implements Runnable{

	private int tickets = 50;

	public static void main(String[] args) throws Exception {
		TestWithVolatile4 tv = new TestWithVolatile4();
		Thread t1 = new Thread(tv);
		Thread t2 = new Thread(tv);
		Thread t3 = new Thread(tv);
		t1.start();
		t2.start();
		t3.start();
	}

	@Override
	public void run() {
		synchronized (this) {
			for(int i=0;i<100;i++){
				if(tickets>0){
					System.out.println("开始售票了..."+tickets--);
				}
			}
		}
		
	}

}