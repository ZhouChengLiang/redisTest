package com.zcl.redisTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhoucl
 *
 */
public class InfiniteRecursion {
    
    public String toString() {
//        return "InfiniteRecursion address: " + this + "\n";
        return "InfiniteRecursion address: " + super.toString() + "\n";
    }
    
    public static void main(String[] args) {
        List<InfiniteRecursion> list = new ArrayList<>();
        for(int i = 0;i < 10; i++) {
            list.add(new InfiniteRecursion());
        }
        System.out.println(list);
        
//        String str = "abc";
//        System.out.println(str.substring(1));
//        System.out.println(String.format("%4$s %3$s %2$s %1$s %4$s %3$s %2$s %1$s","a", "b", "c", "d"));
        System.out.println(String.format("%x",30));
        
    }
}
