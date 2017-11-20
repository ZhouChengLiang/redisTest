package com.zcl.socket.server;

import java.util.Vector;

public class ChatServerManager {
	private ChatServerManager(){}
	
	private static final ChatServerManager chatServerManager = new ChatServerManager();
	
	public static ChatServerManager getChatServerManager(){
		return chatServerManager;
	}
	private Vector<ChatSocket> chatSockets = new Vector<ChatSocket>();
	
	public void addChatSocket(ChatSocket socket){
		chatSockets.add(socket);
	}
	public void removeChatSocket(ChatSocket socket){
		chatSockets.remove(socket);
	}
	public void publish(ChatSocket currentChatSocket,String content){
		for(int i = 0;i<chatSockets.size();i++){
			ChatSocket chatSocket = chatSockets.get(i);
			if(!currentChatSocket.equals(chatSocket)){
				chatSocket.out(content);
			}
		}
	}
}


