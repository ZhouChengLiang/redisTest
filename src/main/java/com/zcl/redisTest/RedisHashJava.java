package com.zcl.redisTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class RedisHashJava {
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		
		Map<String,String> hash = new HashMap<String,String>();
		hash.put("name", "xel");
		hash.put("age", "18");
		hash.put("hobby", "swimming");
		hash.put("address", "HongKong");
		String key = "user:1";
		jedis.hmset(key, hash);
		Map<String,String> result = jedis.hgetAll(key);
		for(Map.Entry entry :result.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}
