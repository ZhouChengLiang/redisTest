package com.zcl.redisTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private Integer status;
	private Integer age;
	private String name;
	private String hobby;
	
	public User(Integer age,String name) {
	    this.age = age;
	    this.name = name;
	}
	
}