package org.zcl.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/**
 * 一、使用NIO完成网络通信的三个核心
 * 
 * 1.通道(Channel):负责连接
 * 		java.nio.channels.Channel 接口:
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--SeverSocketChannel
 * 				|--DatagramChannel
 * 
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 2.缓冲区(Buffer)：负责数据的存取
 * 
 * 3.选择器(Selector)是SelectavleChannel的多路复用器。
 * 用于监控SelectableChannel的IO 状况
 * 
 * @author Administrator
 *
 */
public class TestNonBlockingNIO2 {
	
	//发送端
	@Test
	public void send(){
		try {
			DatagramChannel dc = DatagramChannel.open();
			dc.configureBlocking(false);
			
			ByteBuffer buf = ByteBuffer.allocate(1024);
			Scanner scan = new Scanner(System.in);
			while(scan.hasNext()){
				String str = scan.next();
				buf.put((new Date().toString()+":\n"+str).getBytes());
				buf.flip();
				dc.send(buf, new InetSocketAddress("127.0.0.1", 9898));
				buf.clear();
			}
			dc.close();
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//接收端
	@Test
	public void receive(){
		try {
			DatagramChannel dc = DatagramChannel.open();
			dc.configureBlocking(false);
			dc.bind(new InetSocketAddress(9898));
			Selector selector = Selector.open();
			dc.register(selector, SelectionKey.OP_READ);
			
			while(selector.select() > 0){
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while(it.hasNext()){
					SelectionKey sk = it.next();
					if(sk.isReadable()){
						ByteBuffer buf = ByteBuffer.allocate(1024);
						
						dc.receive(buf);
						buf.flip();
						System.out.println(new String(buf.array(),0,buf.limit()));
					}
				}
				it.remove();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
