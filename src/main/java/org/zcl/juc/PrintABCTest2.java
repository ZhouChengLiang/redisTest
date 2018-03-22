package org.zcl.juc;

public class PrintABCTest2 {
	
	public static void main(String[] args) {
		PrintABC2 printABC = new PrintABC2();
		new Thread(()->{
			for(int i = 0;i<10;i++){
				printABC.printA();
			}
		} , "A").start();
		
		new Thread(()->{
			for(int i = 0;i<10;i++){
				printABC.printB();
			}
		} , "B").start();
		
		new Thread(()->{
			for(int i = 0;i<10;i++){
				printABC.printC();
			}
		} , "C").start();
	}
	
}
class PrintABC2{
	
	private int printStep = 1;
	private Object lock = new Object();
	public void printA(){
		synchronized (lock) {
			while(printStep != 1){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< ");
			printStep = 2;
			lock.notifyAll();
		}
	}
	
	public void printB(){
		synchronized (lock) {
			while(printStep != 2){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< ");
			printStep = 3;
			lock.notifyAll();
		}
	}
	
	public void printC(){
		synchronized (lock) {
			while(printStep != 3){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(" >>> "+Thread.currentThread().getName()+" <<< ");
			printStep = 1;
			lock.notifyAll();
		}
	}
}
