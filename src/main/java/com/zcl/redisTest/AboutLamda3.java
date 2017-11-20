package com.zcl.redisTest;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class AboutLamda3 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(Optional.ofNullable(null).isPresent());
		System.out.println(Optional.ofNullable("").isPresent());
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

		/*String name = "Runoob";
		Integer result = null;

		try {
			nashorn.eval("print('" + name + "')");
			result = (Integer) nashorn.eval("10 + 2");

		} catch (ScriptException e) {
			System.out.println("执行脚本错误: " + e.getMessage());
		}

		System.out.println(result.toString());*/
		testLocalDateTime();
		
		testZonedDateTime();
		try {
	        
	         // 使用基本编码
	         String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
	         System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
	        
	         // 解码
	         byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
	        
	         System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));
	         base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
	         System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);
	        
	         StringBuilder stringBuilder = new StringBuilder();
	        
	         for (int i = 0; i < 10; ++i) {
	            stringBuilder.append(UUID.randomUUID().toString());
	         }
	        
	         byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
	         String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
	         System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
	         
	      }catch(UnsupportedEncodingException e){
	         System.out.println("Error :" + e.getMessage());
	      }
	}

	public static void testLocalDateTime() {

		// 获取当前的日期时间
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("当前时间: " + currentTime);

		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("date1: " + date1);

		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();

		System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);

		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date2: " + date2);

		// 12 december 2014
		LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("date3: " + date3);

		// 22 小时 15 分钟
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4: " + date4);

		// 解析字符串
		LocalTime date5 = LocalTime.parse("20:15:30");
		System.out.println("date5: " + date5);
	}

	public static void testZonedDateTime() {

		// 获取当前时间日期
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("date1: " + date1);

		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);

		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区: " + currentZone);
	}
}
