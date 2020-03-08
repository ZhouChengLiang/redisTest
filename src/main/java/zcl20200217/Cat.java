package zcl20200217;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cat {

    /**
     * 抢单库存缓存 hash 结构
     */
    public static final String RUSH_ORDER_STOCK_CACHE_KEY = "rush_order_stock";

    /**
     * 抢单库存令牌缓存 list 结构
     */
    public static final String RUSH_ORDER_TOKEN_CACHE_KEY = "rush_order_stock_%s_token";

    /**
     * 抢单库存计数器缓存
     */
    public static final String RUSH_ORDER_DO_RUSH_CACHE_KEY = "rush_order_stock_%s_doRush";

    public static void main(String[] args) {
        System.out.println("args = " + String.format(RUSH_ORDER_TOKEN_CACHE_KEY,100000));
        System.out.println("args = " + String.format(RUSH_ORDER_DO_RUSH_CACHE_KEY,100000));
        
        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");
        map.forEach((k,v)->{
            if(k == 3) {
                return;
            }
            System.out.println(k);
        });
        
        System.out.println(String.format("%s-%s", "1","2","3"));
    }
}
