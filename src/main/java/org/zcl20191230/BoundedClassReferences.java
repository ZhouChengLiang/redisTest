package org.zcl20191230;

/**
 * 
 * @author zhoucl
 *
 */
public class BoundedClassReferences {
    
    public static void main(String[] args) {
        Class<? extends Number> bounded = Integer.class;
        bounded = double.class;
    }
}
