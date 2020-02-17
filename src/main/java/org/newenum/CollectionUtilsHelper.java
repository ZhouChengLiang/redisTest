package org.newenum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtilsHelper {
    
    public static void main(String[] args) {
        List<Integer> skuIds_0 = new ArrayList<>(Arrays.asList(1,2,3));
        
        List<Integer> skuIds_2 = new ArrayList<>(Arrays.asList(1,3,4));
        
        List<Integer> skuIds_3 = new ArrayList<>(Arrays.asList(-3));
        
        System.out.println("skuIds_0 before=============== "+skuIds_0);
        System.out.println(skuIds_0.retainAll(skuIds_2));
        System.out.println("skuIds_0 after=============== "+skuIds_0);
        
        System.out.println("skuIds_3 before=============== "+skuIds_3);
        System.out.println(skuIds_3.retainAll(skuIds_2));
        System.out.println("skuIds_3 after=============== "+skuIds_3);
        
        /**
         *  before=============== [1, 2, 3]
            true
            after=============== [1, 3]
         */
    }
}
