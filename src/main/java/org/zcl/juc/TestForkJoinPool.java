package org.zcl.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoinPool {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 100000000L);//305174216704
																		   //5000000050000000
		Long sum = pool.invoke(task);
		System.out.println(sum);
	}
	@Test
	public void test1(){
		Long sum = LongStream.rangeClosed(0L, 100000000L)
				.parallel()
				.reduce(0, Long::sum);
		System.out.println(sum);
	}
}
class ForkJoinSumCalculate extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7150484060186676810L;
	
	private Long start;
	
	private Long end;
	
	private static final Long THURSHOLD = 10000L;
	
	public ForkJoinSumCalculate(Long start,Long end){
		this.start = start;
		this.end = end;
	}
	@Override
	protected Long compute() {
		Long length = end - start;
		if(length<=THURSHOLD){
			Long sum = 0L;
			for(Long i = start;i<=end;i++){
				sum += i;
			}
			return sum;
		}else{
			Long middle = (start + end)/2;
			ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
			left.fork();
			
			ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
			right.fork();
			return left.join()+right.join();
		}
	}
	
}