public class TestWithVolatile2 {
	private static volatile long age = 2L;

	public static void main(String[] args) throws InterruptedException {
		for(int i = 0;i<100;i++){
			new Thread() {

				@Override
				public void run() {
					age++;
					System.out.println(Thread.currentThread().getName()+" add...");
				}
			}.start();
			new Thread() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" age--->"+age);
				}
			}.start();
		}
		
	}

}