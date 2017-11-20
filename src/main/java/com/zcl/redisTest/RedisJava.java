package com.zcl.redisTest;

import redis.clients.jedis.Jedis;

public class RedisJava {
	public static void main(String[] args) {
		//连接本地的Redis服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Server is running: "+jedis.ping());
		System.out.println("Server's Info: "+jedis.info());
		System.out.println(jedis.get("a"));
		
		//设置 redis 字符串数据
	      jedis.set("runoobkey", "Redis tutorial");
	     // 获取存储的数据并输出
	     System.out.println("Stored string in redis:: "+ jedis.get("runoobkey"));
	}
}
