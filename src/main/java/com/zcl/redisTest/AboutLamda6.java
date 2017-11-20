package com.zcl.redisTest;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class AboutLamda6 {
	public static void main(String[] args) {
		Set<String> keys = new HashSet<String>();
		keys.add("A");
		keys.add("B");
		keys.add("C");
		System.out.println(StringUtils.join(keys, ";"));
	}
}
