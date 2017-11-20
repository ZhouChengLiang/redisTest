package com.zcl.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread{
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("已经连接到一个客户端...");
				ChatSocket chatSocket = new ChatSocket(socket);
				chatSocket.start();
				ChatServerManager.getChatServerManager().addChatSocket(chatSocket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
