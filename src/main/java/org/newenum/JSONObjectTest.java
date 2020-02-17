package org.newenum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONObjectTest {
    public static void main(String[] args) {
        JSONObject obj1 = new JSONObject(true);
        for(int i = 1;i<21;i++) {
            obj1.put(String.valueOf(i), i);
        }
        log.info("result1-{}",JSON.toJSONString(obj1));
        JSONObject obj2 = new JSONObject();
        for(int i = 1;i<21;i++) {
            obj2.put(String.valueOf(i), i);
        }
        log.info("result2-{}",JSON.toJSONString(obj2));
    }
}
