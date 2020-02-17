package com.zcl.redisTest;

import java.util.Scanner;

/**
 * 
 * @author zhoucl
 *
 */
public class IntegerMatch {
    
    public static void main(String[] args) {
//        System.out.println("-1234".matches("-?\\d+"));
//        System.out.println("5678".matches("-?\\d+"));
//        System.out.println("+911".matches("-?\\d+"));
//        System.out.println("+911".matches("(-|\\+)?\\d+"));
        
//        System.out.println("abc".matches("^[A-Z].*[.]$"));
//        System.out.println("Abc.".matches("^[A-Z].*[\\.]$"));
        
//        System.out.println("abc".matches("^[A-Z][.]$"));
//        System.out.println("A.".matches("^[A-Z].?[\\.]$"));
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println(name);
        
    }
    
}
