package org.zcl.mytest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author zhoucl
 *
 */
public class AsListInference {
    
    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(new Crusty(),new Slush(),new Power());// It is ok
        
        List<Snow> snow2 = Arrays.asList(new Snow(),new Light(),new Heavy());
        
        List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        Iterator<Integer> it1 = list.iterator();
        while(it1.hasNext()) {
            System.out.print(it1.next());
        }
        System.out.println();
        System.out.println("##############1##################");
        
        ListIterator<Integer> it2 = list.listIterator(list.size());
        while(it2.hasPrevious()) {
            System.out.print(it2.previous());
        }
        
        System.out.println();
        System.out.println("###############2#################");
        
        ListIterator<Integer> it3 = list.listIterator(list.size());
        while(it3.hasPrevious()) {
            System.out.print(it3.previous()+" , "+it3.nextIndex()+" , "+it3.previousIndex());
        }
        System.out.println();
        System.out.println("###############3#################");
        
        ListIterator<Integer> it4 = list.listIterator();
        while(it4.hasNext()) {
            System.out.print(it4.next()+" , "+it4.nextIndex()+" , "+it4.previousIndex());
        }
        
    }
}
class Snow{}
class Power extends Snow{}
class Light extends Power{}
class Heavy extends Power{}
class Crusty extends Snow{}
class Slush extends Snow{}