package com.zcl.socket.client;

import java.awt.EventQueue;

import com.zcl.redisTest.User;

public class StartClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User currentUser = new User();
					currentUser.setName("大师");
					MainWIndow frame = new MainWIndow();
					frame.setCurrentUser(currentUser);
					frame.setVisible(true);
					ChatManager.getCM().setWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}