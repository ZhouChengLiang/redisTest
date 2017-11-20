import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) throws IOException, InterruptedException {
			CyclicBarrier barrier = new CyclicBarrier(6, new Runnable() {
				@Override
				public void run() {
					System.out.println("This is real things to do...");
				}
			});
			ExecutorService executor = Executors.newFixedThreadPool(30);
			executor.submit(new Thread(new Runner(barrier, "1号选手")));
			executor.submit(new Thread(new Runner(barrier, "2号选手")));
			executor.submit(new Thread(new Runner(barrier, "3号选手")));
			executor.submit(new Thread(new Runner(barrier, "4号选手")));
			executor.submit(new Thread(new Runner(barrier, "5号选手")));
			executor.submit(new Thread(new Runner(barrier, "6号选手")));
	}
}

class Runner implements Runnable {
	// 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
	private CyclicBarrier barrier;

	private String name;

	public Runner(CyclicBarrier barrier, String name) {
		super();
		this.barrier = barrier;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000 * (new Random()).nextInt(8));
			System.out.println(name + " Ready...");
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}