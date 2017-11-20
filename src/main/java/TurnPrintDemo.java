public class TurnPrintDemo {

	private static Object lock = new Object();
	private static int maxTimes = 10;

	public static void main(String[] args) throws Exception {
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < maxTimes; i++) {
					synchronized (lock) {
						lock.notify();
						System.out.println(Thread.currentThread().getName() + " A ");
						try {
							if(i<maxTimes-1){
								lock.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < maxTimes; i++) {
					synchronized (lock) {
						lock.notify();
						System.out.println(Thread.currentThread().getName() + " B ");
						try {
							if(i<maxTimes-1){
								lock.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
}