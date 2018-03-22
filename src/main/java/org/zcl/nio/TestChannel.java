package org.zcl.nio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

/**
 * 一、通道(Channel) 用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输
 * Channel 本身不存储数据,因此需要配合缓冲区进行传输
 * @author Administrator
 *一、通道的主要实现类
 *	java.nio.channels.Channel接口:
 *		|--FileChanel
 *		|--SocketChannel
 *		|--ServerSocketChannel
 *		|--DatagramChannel
 *二、获取通道
 *1.Java 针对支持通道的类提供了getChannel()方法
 *  本地IO
 *  FileInputStream/FileOutputStream
 *  RandomAccessFile
 *  
 *  网络IO
 *  Socket
 *  ServerSocket
 *  DatagramSocket
 * 
 * 2、在JDK1.7中的NIO2针对通道提供了静态方法 open()
 * 	                                    的Files 工具类的newByteChannel()
 * 
 * 四、通信之间的数据传输
 * transferFrom()
 * transferTo()
 * 
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取(Scattering Reads) 将通道中的数据分散到多个缓冲区中
 * 聚集写入(Gathering Writes) 将多个缓冲区中的数据聚集到通道中
 * 
 * 六、字符集:Charset
 * 编码:字符串 -> 字节数组
 * 解码:字节数组->字符串 
 */
public class TestChannel {
	
	//字符集
	@Test
	public void test5(){
		Charset cs1 = Charset.forName("GBK");
		
		//获取编码器
		CharsetEncoder ce = cs1.newEncoder();
		
		//获取解码器
		CharsetDecoder cd = cs1.newDecoder();
		
		CharBuffer cBuf = CharBuffer.allocate(1024);
		cBuf.put("大舅哥威武！");
		cBuf.flip();
		
		//编码
		try {
			ByteBuffer bBuf = ce.encode(cBuf);
			for(int i = 0; i < 12;i++){
				System.out.println(bBuf.get());
			}
			
			//解码
			bBuf.flip();
			CharBuffer cBuf2 = cd.decode(bBuf);
			System.out.println(cBuf2.toString());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test4(){
		Map<String,Charset> map = Charset.availableCharsets();
		
		Set<Entry<String,Charset>> set = map.entrySet();
		for (Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey()+" = "+entry.getValue());
		}
	}
	
	@Test
	public void test3(){
		try {
			RandomAccessFile raf = new RandomAccessFile("zcl.txt", "rw");
			
			//1.获取通道
			FileChannel channel1 = raf.getChannel();
			
			//2.分配指定大小的缓冲区
			ByteBuffer buf1 = ByteBuffer.allocate(100);
			ByteBuffer buf2 = ByteBuffer.allocate(1024);
			
			//3.分散读取
			ByteBuffer[] bufs = {buf1,buf2};
			channel1.read(bufs);
			for(ByteBuffer byteBuffer :bufs){
				byteBuffer.flip();
			}
			System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
			System.out.println("------------------------------------------");
			System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
			
			//4.聚集写入
			RandomAccessFile raf1 = new RandomAccessFile("xel.txt", "rw");
			FileChannel channel2 = raf1.getChannel();
			channel2.write(bufs);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//通道之间的数据传输(直接缓冲区)
	@Test
	public void test2() throws Exception{
		FileChannel inChannel = FileChannel.open(Paths.get("zcl.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("zcl_copy_1.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
//		inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());
		inChannel.close();
		outChannel.close();
	}
	
	@Test
	public void test7() throws Exception{
		Instant start = Instant.now();
		List<String> fileNames = Arrays.asList("zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt",
				"xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt",
				"xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt",
				"xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt");
		StringBuilder sb = new StringBuilder();
		InputStream input = null;
        for(String infile:fileNames){
            File fin = new File(infile) ;
            input = new BufferedInputStream(new FileInputStream(fin));
            // 获得缓存读取流开始的位置  
            int len = input.read();  
            System.out.println("len=" + len);  
  
            // 定义一个容量来盛放数据  
            byte[] buf = new byte[1024];  
  
            while ((len = input.read(buf)) != -1) {  
                // 如果有数据的话，就把数据添加到输出流  
                //这里直接用字符串StringBuffer的append方法也可以接收  
                 sb.append(new String(buf,0,len));
            }  
            input.close();
        }
        System.out.println("The result>>>>>>>"+sb.toString());
        System.out.println(fileNames.size()+"Files BIO cost>>>>>>>>>>>>>>>>>>"+Duration.between(start, Instant.now()));
	}
	
	/**
	 * 读取多个文件内容写到字符串内  FIXME  NIO?
	 * @throws Exception 
	 */
	@Test
	public void test6() throws Exception{
		Instant start = Instant.now();
		List<String> fileNames = Arrays.asList("zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt",
				"xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt",
				"xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt",
				"xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt",
				"zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt","xel.txt","lib2lzzz.txt","zcl.txt");
		File out = new File("total.txt");
		FileChannel mFileChannel = new FileOutputStream(out).getChannel();
        FileChannel inFileChannel;
        for(String infile:fileNames){
            File fin = new File(infile) ;
            inFileChannel = new FileInputStream(fin).getChannel();
            inFileChannel.transferTo(0, inFileChannel.size(),mFileChannel);
            inFileChannel.close();
        }
        FileChannel resultChanel =  FileChannel.open(Paths.get("total.txt"), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate((int) resultChanel.size());
        resultChanel.read(buf);
        System.out.println(new String(buf.array(),0,buf.array().length));
        
        mFileChannel.close();
        resultChanel.close();
        System.out.println(fileNames.size()+"Files NIO cost>>>>>>>>>>>>>>>>>>"+Duration.between(start, Instant.now()));
	}
	
	//2.使用直接缓冲区完成文件的复制(内存映射文件)
	@Test
	public void test1() throws Exception{
		FileChannel inChannel = FileChannel.open(Paths.get("zcl.txt"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("zcl_copy_1.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		//内存映射文件
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		//直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inMappedBuf.limit()];
		inMappedBuf.get(dst);
		outMappedBuf.put(dst);
		
		inChannel.close();
		outChannel.close();
	}
	
	//1.利用通道完成文件的复制
	@Test
	public void test0(){
		FileInputStream fis = null;
		FileOutputStream fos = null;
				                      
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("zcl.txt");
			fos = new FileOutputStream("zcl_copy.txt");
			
			//获取通道
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			
			//分配缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			//将通道中的数据存入缓冲区
			while(inChannel.read(buf)!= -1){
				//切换成读取数据模式
				buf.flip();
				//将缓冲区的数据写入通道
				outChannel.write(buf);
				buf.clear();//清空缓冲区
			}
			
		} catch (Exception e) {
			
		}finally{
			try {
				outChannel.close();
				inChannel.close();
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
