package org.zcl20191230;

import java.util.Random;

/**
 * 
 * @author zhoucl
 *
 */
public class ClassInitialization {
    
    public static Random rand = new Random(47);
    
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("unused")
        Class initbale = Initable.class;
        System.out.println("##########before Initable.staticFinal################");
        System.out.println(Initable.staticFinal);
        System.out.println("##########before Initable.staticFinal2################");
        System.out.println(Initable.staticFinal2);
        System.out.println("##########before Initable2.staticNonFinal################");
        System.out.println(Initable2.staticNonFinal);
        System.out.println("##########before Class.forName(\"Initable3\")################");
//        Class initable3 = Class.forName("org.zcl20191230.Initable3");
        System.out.println(Initable3.staticNonFinal);
//        System.out.println(Initable3.staticNonFinal2);
    }
}

class Initable{
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2{
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3{
    static int staticNonFinal = 74;
    static final int staticNonFinal2 = 75;
    static {
        System.out.println("Initializing Initable3");
    }
}
