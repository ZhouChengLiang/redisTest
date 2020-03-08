package zcl20200217;

import java.util.HashMap;

public class Client {
    
    public static void main(String[] args) {
//        invoker1();
        invoker2();
    }
    
    @SuppressWarnings("rawtypes")
    public static void invoker1() {
     // 父类存在的地方,子类就应该能够存在
        Father f = new Father();
        HashMap map = new HashMap();
        f.doSomething(map);  
    }
    
    @SuppressWarnings("rawtypes")
    public static void invoker2() {
        // 父类存在的地方,子类就应该能够存在
//        Son f = new Son();
        Father f = new Son();
        HashMap map = new HashMap();
        f.doSomething(map);  
    }
}
