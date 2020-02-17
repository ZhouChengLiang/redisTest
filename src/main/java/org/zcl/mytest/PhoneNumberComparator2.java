package org.zcl.mytest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneNumberComparator2 {
    
    public static void main(String[] args) {
        PhoneNumber2 pn0 = new PhoneNumber2(330000,1,1);
        PhoneNumber2 pn1 = new PhoneNumber2(333000,3,3);
        PhoneNumber2 pn2 = new PhoneNumber2(332000,2,2);
        PhoneNumber2 pn3 = new PhoneNumber2(332000,3,1);
        List<PhoneNumber2> list = new ArrayList<>();
        list.add(pn0);
        list.add(pn1);
        list.add(pn2);
        list.add(pn3);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        
    }
}
