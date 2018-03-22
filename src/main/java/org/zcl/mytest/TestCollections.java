package org.zcl.mytest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Supplier;

import org.junit.Test;

public class TestCollections {
	public static void main(String[] args) {
		List list = Collections.synchronizedList(new ArrayList<>());
		Vector v = new Vector<>();
		Map map = new HashMap<>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		map.put("D", "4");
		map.put("E", "5");
		map.put("F", "6");
		Map<String,String> result = Collections.unmodifiableMap(map);
		System.out.println(result);
		result.put("A", "11");
		System.out.println(result);
	}
	public void aaa (String key,Supplier<Object> supplier){
		if(!"123".equals(key)){
		}
		System.out.println(supplier.get());
	}
	@Test
	public void test2(){
		aaa("123",()->{System.out.println("2222222222222222");return 33333333333L;});
	}
	@Test
	public void test1(){
		Object a = 1000L;
		Long b = 1000L;
		int e = 1000;
		System.out.println(Objects.equals(e, b));
		
		Object c = "OK";
		String d = "OK";
		System.out.println(Objects.equals(c, d));
	}
	
	/*@Test
	public void test31(){
		String str = "";
		for(int i = 0;i<10;i++){
			str += UUID.randomUUID().toString();
		}
		System.out.println("str>>>>>>>>>>>>>>>>>>>>>>>"+str);
		System.out.println("***************************************");
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<10;i++){
			sb.append(UUID.randomUUID().toString());
		}
		System.out.println("sb>>>>>>>>>>>>>>>>>>>>>>>>"+sb.toString());
	}*/
}
