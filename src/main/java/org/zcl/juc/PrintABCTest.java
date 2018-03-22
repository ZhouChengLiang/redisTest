package org.zcl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCTest {
	
	public static void main(String[] args) {
		PrintABC printABC = new PrintABC();
		new Thread(()->{
			for(int i = 1;i<11;i++){
				printABC.printA(i);
			}
		} , "A").start();
		
		new Thread(()->{
			for(int i = 1;i<11;i++){
				printABC.printB(i);
			}
		} , "B").start();
		
		new Thread(()->{
			for(int i = 1;i<11;i++){
				printABC.printC(i);
			}
		} , "C").start();
	}
	
}
class PrintABC{
	private Lock lock  = new ReentrantLock();
	
	private Condition conditionA = lock.newCondition();
	
	private Condition conditionB = lock.newCondition();
	
	private Condition conditionC = lock.newCondition();
	
	private int printStep = 1;
	
	public void printA(int i){
		lock.lock();
		try {
			while(printStep != 1){
				conditionA.await();
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< "+i);
			printStep = 2;
			conditionB.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void printB(int i){
		lock.lock();
		try {
			while(printStep !=2){
				conditionB.await();
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< "+i);
			printStep = 3;
			conditionC.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void printC(int i){
		lock.lock();
		try {
			while(printStep !=3){
				conditionC.await();
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< "+i);
			printStep = 1;
			conditionA.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
