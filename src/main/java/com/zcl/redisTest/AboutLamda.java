package com.zcl.redisTest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AboutLamda {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<User> users = new ArrayList<User>();
		users.add(new User(20, "张三"));
		users.add(new User(22, "李四"));
		users.add(new User(10, "王五"));
		users.add(new User(30, "赵六"));
		users.add(new User(21, "马七"));

		Stream<User> stream = users.stream();
		//
		stream = stream.filter((User user) -> user.getAge() > 20);
		List<User> result_list = stream.collect(Collectors.toList());
		result_list.forEach((User user) -> System.out.println("A----->age: "+user.getAge()+" name: "+user.getName()));
		//		long a = stream.filter((User user) -> user.getAge() > 20).map((User user) -> {
//			return user;
//		}).count();
		
		Stream<User> stream_ = users.stream();
		stream_.map((User user) ->
			{ user.setName(UUID.randomUUID().toString()+user.getName());
				return user;});
		result_list.forEach((User user) -> System.out.println("B---->age: "+user.getAge()+" name: "+user.getName()));
		result_list.forEach((User user) -> {user.setName(UUID.randomUUID().toString()+user.getName());System.out.println("C---->age: "+user.getAge()+" name: "+user.getName());} );
		
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		Integer[] evens = Stream.of(sixNums)
				.filter(n -> n%2 == 0)
				.toArray(Integer[]::new);
		
		List<String> str_list = Stream.of("one", "two", "three", "four")
		 .filter(e -> e.length() > 3)
		 .peek(e -> System.out.println("Filtered value: " + e))
		 .map(String::toUpperCase)
		 .peek(e -> System.out.println("Mapped value: " + e))
		 .collect(Collectors.toList());
		
		String str = "中文的话";
		try {
			String str1 = new String(str.getBytes("utf8"), "iso8859-1");
			if(str1.equals(new String(str.getBytes("utf8"), "iso8859-1"))){
				System.out.println("before the str1: "+str1);
				System.out.println("*****************************");
				String str2 =new String(str1.getBytes("iso8859-1"), "utf-8");
				System.out.println("after the str1: "+str2);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
