package com.zcl.redisTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AboutLamda4 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		List<Integer> currentIds = new ArrayList<Integer>();
		currentIds.add(1);
		currentIds.add(2);
		currentIds.add(3);
		List<Jhon> other = currentIds.stream().map(AboutLamda4::getJackList).map(Jhon::new).collect(Collectors.toList());
		System.out.println(other);
		
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
	}
	public static Jack getJackList(Integer id){
		Jack jack = new Jack();
		jack.setAge(id);
		jack.setName(UUID.randomUUID().toString()+id);
		return jack;
		
	}
		
}

@Getter
@Setter
@ToString
class Jack{
	public int age;
	public String name;
}
@Getter
@Setter
@ToString
class Jhon{
	public int age;
	public String name;
	public Jhon(Jack jack){
		age = jack.getAge();
		name = jack.getName();
	}
}