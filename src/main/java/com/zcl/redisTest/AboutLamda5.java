package com.zcl.redisTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AboutLamda5 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String str = "aaa";
		test_1(str,()->test_2());
	}
	public static void test_1(String str,Supplier<List<User_>> user_list){
		List<User_> aaa = user_list.get();
		System.out.println(aaa);
	}
	public static List<User_> test_2(){
		List list = new ArrayList();
		User_ u1 = new User_();
		u1.setAge(1);
		u1.setName("u1");
		list.add(u1);
		return list;
	}
}
@Setter
@Getter
@ToString
class User_{
	String name;
	int age;
}
