package com.zcl.redisTest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class User {
	private int age;
	private String name;
	
	public User(){
		
	}
	public User(int age,String name){
		this.age = age;
		this.name = name;
	}
}
