package org.zcl.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
public class TestBlockingNIO3 {
	
	//客户端
	@Test
	public void client(){
		try {
			SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
			FileChannel inChannel = FileChannel.open(Paths.get("zcl.txt"), StandardOpenOption.READ);
			ByteBuffer buf = ByteBuffer.allocate(1024);
			while(inChannel.read(buf) != -1){
				buf.flip();
				sChannel.write(buf);
				buf.clear();
			}
			sChannel.shutdownOutput();
			//接收服务端的反馈
			int len = 0;
			while((len = sChannel.read(buf)) != -1){
				buf.flip();
				System.out.println(new String(buf.array(),0,len));
				buf.clear();
			}
			inChannel.close();
			sChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//服务端
	@Test
	public void server(){
		try {
			ServerSocketChannel ssChannel = ServerSocketChannel.open();
			FileChannel outChannel = FileChannel.open(Paths.get("lib2lzzz_forver.txt"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			ssChannel.bind(new InetSocketAddress(9898));
			SocketChannel sChannel = ssChannel.accept();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			while(sChannel.read(buf) != -1){
				buf.flip();
				outChannel.write(buf);
				buf.clear();
			}
			//发送反馈到客户端
			buf.put("服务端接收数据成功".getBytes());
			buf.flip();
			sChannel.write(buf);
			sChannel.close();
			outChannel.close();
			ssChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
