package org.zcl.mytest;

import java.util.Vector;

import org.junit.Test;

public class SafePoint {
	private int x,y;
	
	public SafePoint(int[] a){
		this(a[0],a[1]);
	}
	public SafePoint(int x,int y){
		this.x = x;
		this.y = y;
	}
	public SafePoint(SafePoint p){
		this(p.get());
	}

	public synchronized void set(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public synchronized int[] get() {
		return new int[] {x,y};
	}	
	
	@Test
	public void test0(){
		Vector v = new Vector<>();
		
	}
}
