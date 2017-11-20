package org.zcl.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 一、缓存区(Buffer)：在Java NIO中 负责数据的存取,缓存区就是数组。用于存储不同数据类型的数据
 * 根据数据类型不同,提供了相应类型的缓存区
 * ByteBuff
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 
 * 上述缓冲区的管理方式几乎一致,通过allocate()获取缓冲区
 * @author Administrator
 *
 *二、缓冲区存取数据的两个核心方法
 *put():存入数据到缓冲区中
 *get():获取缓冲区的数据
 *
 *三、缓冲区中的四个核心属性
 *capacity:容量,表示缓冲区中最大存储数据的容量,一旦声明不能改变
 *limit:界限,表示缓冲区中可以操作数据的大小。(limit后数据不能进行读写)
 *position:位置，表示缓冲区中正在操作数据的位置
 *
 *mark：标记
 *position <= limit <=capacity
 *
 *四、直接缓冲区与非直接缓冲区
 *非直接缓冲区:通过allocate()方法分配缓冲区,将缓冲区建立在JVM的内存中
 *直接缓冲区:通过allocateDirect()方法分配直接缓冲区,将缓冲区建立在物理内存中
 *可以提高效率
 */
public class TestBuffer {
	
	@Test
	public void test2(){
		//分配直接缓冲区
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		
		System.out.println(buf.isDirect());
	}
	
	@Test
	public void test1(){
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put(str.getBytes());
		buf.flip();
		
		byte[] dst = new byte[buf.limit()];
		buf.get(dst, 0, 2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());
		
		buf.mark();
		buf.get(dst, 2, 2);
		System.out.println(new String(dst,2,2));
		System.out.println(buf.position());
		
		buf.reset();
		System.out.println(buf.position());
		
		if(buf.hasRemaining()){
			System.out.println(buf.remaining());
		}
	}
	
	@Test
	public void test0(){
		String str = "abcd";
		//1.分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("------------------------allocate-------------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//2.利用put() 存入数据到缓冲区中
		buf.put(str.getBytes());
		System.out.println("----------------put----------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		
		//3.切换成读取数据的模式 flip
		System.out.println("-------------flip()切换成读取数据的模式------------");
		buf.flip();
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//4.利用get()读取缓冲区中的数据
		System.out.println("---------------利用get()读取缓冲区中的数据----------------");
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println(new String(dst, 0, dst.length));
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		System.out.println("----------rewind()---------------");
		buf.rewind();
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//6.clear() 清空缓冲区
		System.out.println("----------clear() 清空缓冲区--------");
		buf.clear();
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println((char)buf.get());
	}
}
