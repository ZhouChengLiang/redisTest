package org.zcl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCTest3 {
	
	public static void main(String[] args) {
		PrintABC3 printABC = new PrintABC3();
		
		new Thread(()->{
			for(int i = 0;i<10;i++){
				printABC.printA(i);
			}
		} , "A").start();
		
		new Thread(()->{
			for(int i = 0;i<10;i++){
				printABC.printB(i);
			}
		} , "B").start();
		
		new Thread(()->{
			for(int i = 0;i<10;i++){
				printABC.printC(i);
			}
		} , "C").start();
	}
	
}
class PrintABC3{
	private Lock lock  = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private int printStep = 1;
	
	public void printA(int i){
		lock.lock();
		try {
			while(printStep != 1){
				condition.await();
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< "+i);
			printStep = 2;
			condition.signalAll();
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
				condition.await();
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< "+i);
			printStep = 3;
			condition.signalAll();
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
				condition.await();
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< "+i);
			printStep = 1;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
