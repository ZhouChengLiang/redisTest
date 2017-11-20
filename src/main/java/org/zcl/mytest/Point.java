package org.zcl.mytest;

public class Point {
	private final int x,y;
	
	public Point(int[] a){
		this(a[0],a[1]);
	}
	public Point(int x,int y){
		this.x = x;
		this.y = y;
	}
	public Point(Point p){
		this(p.get());
	}

	public synchronized int[] get() {
		return new int[] {x,y};
	}	
	
}
