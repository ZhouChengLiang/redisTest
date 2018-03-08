import javax.annotation.PostConstruct;

public class TestClient {
	private Thread thread;
	
	@PostConstruct
	public void start() {
		thread = new Thread(this::process);
		thread.start();
	}
	private void process(){
		System.out.println("do....something");
	}
	public static void main(String[] args) {
		TestClient tt = new TestClient();
//		tt.start();
	}
}
