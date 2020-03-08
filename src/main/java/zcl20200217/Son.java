package zcl20200217;

import java.util.Collection;
import java.util.HashMap;

public class Son extends Father {
    
    // 放大输入参数类型  此处是重载了父类的方法
    @SuppressWarnings("rawtypes")
    public Collection doSomething(HashMap map) {
        System.out.println("子类被执行...");
        return map.values();
    }
}
