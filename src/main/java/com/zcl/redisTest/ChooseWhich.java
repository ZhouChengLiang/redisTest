package com.zcl.redisTest;

import java.util.List;
import java.util.ServiceLoader;

public class ChooseWhich {
	public static void main(String[] args) {
		ServiceLoader<Search> sl = ServiceLoader.load(Search.class);
		
	}
}
interface Search{
	List search(String keyword);
}