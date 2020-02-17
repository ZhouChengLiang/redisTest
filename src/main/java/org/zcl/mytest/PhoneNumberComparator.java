package org.zcl.mytest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PhoneNumberComparator {
    
    private static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparingInt(( PhoneNumber pn) -> pn.getAreaCode())
            .thenComparing(pn -> pn.getPrefix())
            .thenComparing(pn -> pn.getLineNumer());
    
    private static final Comparator<PhoneNumber> COMPARATOR0 = Comparator.comparingInt(( PhoneNumber pn) -> pn.getAreaCode());
    
    private static final Comparator<PhoneNumber> COMPARATOR1 = Comparator.comparingInt((PhoneNumber pn) -> pn.getPrefix()).reversed();
    
    private static final Comparator<PhoneNumber> COMPARATOR2 = Comparator.comparingInt(( PhoneNumber pn) -> pn.getLineNumer());
    
    private static final Comparator<PhoneNumber> COMPARATOR3 = COMPARATOR0.thenComparing(COMPARATOR1).thenComparing(COMPARATOR2);
    
    
    public int compareTo(PhoneNumber pn1 ,PhoneNumber pn2) {
        return COMPARATOR.compare(pn1, pn2);
    }
    
    public static void main(String[] args) {
        PhoneNumber pn0 = new PhoneNumber(330000,1,1);
        PhoneNumber pn1 = new PhoneNumber(333000,3,3);
        PhoneNumber pn2 = new PhoneNumber(332000,2,2);
        PhoneNumber pn3 = new PhoneNumber(332000,3,1);
        List<PhoneNumber> list = new ArrayList<>();
        list.add(pn0);
        list.add(pn1);
        list.add(pn2);
        list.add(pn3);
        System.out.println(list);
        list.sort(COMPARATOR3);
        System.out.println(list);
        
    }
}
