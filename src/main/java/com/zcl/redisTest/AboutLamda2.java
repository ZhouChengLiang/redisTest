package com.zcl.redisTest;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AboutLamda2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String str = "自助餐";
		String str_ = new String(str.getBytes("utf-8"), "ISO-8859-1");
		try {
			System.out.println(Stream.of(str)
							.map(AboutLamda2::jjjj)
							.iterator().next());
			System.out.println(Stream.of(str_)
					.map(AboutLamda2::convertGetParam)
					.iterator().next());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String convertGetParam(String str)  {
		if(str == null)
			return null;
		try {
			return new String(str.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public static String jjjj(String str)  {
		StringBuilder sb = new StringBuilder();
		sb.append(str).append(UUID.randomUUID().toString());
		return sb.toString();
	}
}
