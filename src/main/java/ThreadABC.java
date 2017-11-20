
public class ThreadABC  extends Thread{
	int i=0;
	private static int count=0;
	private static Object o=new Object();

	public ThreadABC(String ID){
		currentThread().setName(ID);
	}
	public void run() {
		synchronized (o) {
		    for(int i=0;i<10;i++){
		    	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(count%2==0&&currentThread().getName().equals("A")){
					o.notify();
					System.out.print(currentThread().getName());
					count++;
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if (count%2==1&&currentThread().getName().equals("B")) {
					o.notify();
					System.out.print(currentThread().getName());
					count++;
					try {
						o.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				}
		    }
		}

	
        public static void main(String[] args) {
        	 ThreadABC b=new ThreadABC("B");
        	 ThreadABC a=new ThreadABC("A");
		    a.setName("A");
		    b.setName("B");
		    a.start();
		    b.start();
		   
		}
}

