package com.zcl.redisTest;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class ZclTest02 {
    public static void main(String[] args) throws Exception {
        String orderId = "2019082700012869";
        
        System.out.println( Math.abs(orderId.hashCode())  % 16);
        
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("A", 41);
        map.put("B", 45);
        map.put("C", 45);
        map.put("D", 45);
        map.put("E", 45);
        System.out.println(JSON.toJSONString(map));
        String str = JSON.toJSONString(map);
        Map<String,Integer> result = JSON.parseObject(str, LinkedHashMap.class);
        System.out.println(result);
        
    }

}
