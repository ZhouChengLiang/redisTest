package org.zcl20191230;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author zhoucl
 *
 */
public class PriorityQueueDemo {
    
    public static void main(String[] args) {
        Set<Character> charSet = new HashSet<>();
        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        for(char c : fact.toCharArray()) {
            charSet.add(c);
        }
        System.out.println(charSet);
        Collections.reverseOrder();
        System.out.println("#################1################");
        for(Map.Entry<String, String> entry:System.getenv().entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        System.out.println("#################2################");
        
    }

}
