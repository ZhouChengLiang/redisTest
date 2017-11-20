package org.zcl.mytest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TestOrderByFiled {
	
	@Test
	public void test0(){
		List<String> articleIds = Arrays.asList("1","2","3","4","5","6");
		String ids = articleIds.stream().collect(Collectors.joining(",", "(", ")"));
		String sort = articleIds.stream().collect(Collectors.joining(",", "(ARTICLE_ID,", ")"));
		System.out.println(ids);
		System.out.println(sort);
	}
	
	@Test
	public void test1(){
		
	}
}
