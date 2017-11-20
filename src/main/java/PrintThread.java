
public class PrintThread {
	public static void main(String[] args) {
		
		
		for(int i=0;i<100;i++){
			Thread printA = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("A");

				}
			});
			Thread printB = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("B");

				}
			});
			printA.start();
			printB.start();
		}
		
	}
}
