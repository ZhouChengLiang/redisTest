package zcl20200217;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zhoucl
 *
 */
public class Father {
    
    @SuppressWarnings("rawtypes")
//    public Collection doSomething(HashMap map) {
        public Collection doSomething(Map map) {
        System.out.println("父类被执行...");
        return map.values();
    }

}
