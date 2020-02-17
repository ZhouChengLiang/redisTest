package org.newenum;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JSONMap2 {
    
    public static void main(String[] args) {
       List<Integer> list = Arrays.asList(1);
       System.out.println(JSON.toJSONString(list));
    }
    
}


