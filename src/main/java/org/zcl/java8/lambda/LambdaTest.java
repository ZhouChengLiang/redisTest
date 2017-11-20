package org.zcl.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

public class LambdaTest {
	@Test
	public void test0(){
		List<Integer> commentIds = Arrays.asList(1,2,3,4,5,6,7,8);
    	int[] commentIdsArr = commentIds.stream().mapToInt(Integer::intValue).toArray();
    	System.out.println(commentIdsArr.length);
    	Arrays.stream(commentIdsArr).forEach(System.out::println);
	}
	@Test
	public void test1(){
		helper("abc","bcd",(x1)->x1.toUpperCase());
	}
	public void helper(String str1,String str2,Function<String, String> myFun){
		System.out.println(myFun.apply(str2));
	}
}
