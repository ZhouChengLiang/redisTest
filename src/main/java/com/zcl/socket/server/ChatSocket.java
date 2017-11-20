package com.zcl.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread{
	private Socket socket;
	public ChatSocket (Socket socket){
		this.socket = socket;
	}
	
	public void out(String content){
		try {
			socket.getOutputStream().write((content+" \n").getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("断开了客户端链接");
			ChatServerManager.getChatServerManager().removeChatSocket(this);
		}
	}
	
	@Override
	public void run() {
		out("已经连接到本服务器了");
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream(),"gbk"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				ChatServerManager.getChatServerManager().publish(this, line);
			}
			br.close();
			System.out.println("断开了一个客户端链接");
			ChatServerManager.getChatServerManager().removeChatSocket(this);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			ChatServerManager.getChatServerManager().removeChatSocket(this);
			e.printStackTrace();
		}	
	}

}
