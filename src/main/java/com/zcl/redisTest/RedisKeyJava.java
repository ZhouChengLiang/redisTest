package com.zcl.redisTest;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisKeyJava {
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");

		// 获取数据并输出
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			switch (jedis.type(key)) {
			case "string":
				System.out.println(key+": "+jedis.get(key));
				break;
			case "list":
				System.out.println(key+": "+jedis.lrange(key, 0, jedis.llen(key)));
				break;
			default:
				break;
			}
		}
		jedis.close();
	}
}
