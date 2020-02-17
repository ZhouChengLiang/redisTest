package org.zcl20191230;

import java.util.StringTokenizer;

/**
 * 
 * @author zhoucl
 *
 */
public class ReplacingStringTokenizer {
    
    public static void main(String[] args) {
        String input = "A is A ; B is B";
        StringTokenizer stoke = new StringTokenizer(input);
        while(stoke.hasMoreElements()) {
            System.out.println(stoke.nextToken());
        }
    }
}
