package org.zcl20191230;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author zhoucl
 *
 * @param <T>
 */
public class ReversibleArrayList<T> extends ArrayList<T> {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7845123480010227723L;
    
    private Collection<T> c;
    
    public ReversibleArrayList(Collection<T> c) {
        super(c);
        this.c = c;
    }
    
    public Iterable<T> reversedABC(){
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    
                    int current = size() -1;
                    
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public T next() {
                        return get(current--);
                    }
                };
            }
            
        };
    }
    
    public Iterable<T> randomized(){
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                List<T> shuffled = new ArrayList<T>(c);
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
            
        };
    }
    
    public static void main(String[] args) {
        ReversibleArrayList<String> list = new ReversibleArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8"));
        System.out.println(list);
        for(String s:list.reversedABC()) {
            System.out.print(s);
        }
//        Collections.shuffle(list,new Random(47));
//        System.out.println(list);
//        Collections.shuffle(list,new Random(47));
//        System.out.println(list);
        System.out.println();
        System.out.println("#########################");
        for(String str : list.randomized()) {
            System.out.print(str);
        }
        System.out.println();
        for(String str : list.randomized()) {
            System.out.print(str);
        }
        System.out.println();
        System.out.println("#########################");
        System.out.println(list);
    }
    
}
