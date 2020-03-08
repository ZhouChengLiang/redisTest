package zcl20200217;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhoucl
 * 2020/3/8 上午10:40
 */
public class CollectOpTest {

    public static void main(String[] args) {



        List<Order> orders = new ArrayList<Order>(){
            private static final long serialVersionUID = 6174493676200003297L;
            {
                add(new Order(1,"zcl1",2,new BigDecimal("101")));
                add(new Order(2,"zcl2",1,new BigDecimal("102")));
                add(new Order(3,"zcl2",1,new BigDecimal("103")));
                add(new Order(4,"zcl1",2,new BigDecimal("104")));
                add(new Order(5,"zcl1",3,new BigDecimal("106")));
            }
        };
        System.out.println("JSON.toJSONString(orders) = " + JSON.toJSONString(orders));

//       orders.stream().collect(()-> {return new HashMap<String,Order>();},
//        (HashMap<String,Order> map,Order newOrder)->{
//           String account = newOrder.getAccount();
//           if(map.containsKey(account)){
//              Order order =  map.get(account);
//               map.put(account,new Order(0,account,order.getBuyAmount()+newOrder.getBuyAmount(),
//                       order.getMoney().add(newOrder.getMoney())));
//           }else{
//               map.put(account,newOrder) ;
//           }
//        },(HashMap<String,Order> map1,HashMap<String,Order> map2)->{
//
//            map2.forEach((key,value)->{
//                map1.merge(key,value,(order1,order2)->{
//                        return new Order(0,key,
//                                order1.getBuyAmount()+order2.getBuyAmount(),
//                                order1.getMoney().add(order2.getMoney()));
//                });
//            });
//        });

//        Map<String,Order> result = orders.stream().collect(HashMap::new,
//                (HashMap<String, Order> map, Order newOrder) -> {
//                    String account = newOrder.getAccount();
//                    if (map.containsKey(account)) {
//                        Order order = map.get(account);
//                        map.put(account, new Order(0, account, order.getBuyAmount() + newOrder.getBuyAmount(),
//                                order.getMoney().add(newOrder.getMoney())));
//                    } else {
//                        map.put(account, newOrder);
//                    }
//                }, (HashMap<String, Order> map1, HashMap<String, Order> map2) -> map2.forEach((key, value) -> map1.merge(key, value, Order::getOrder))
//        );

        Map<String,Order> result = orders.stream().collect(HashMap::new,
                (HashMap<String, Order> map, Order newOrder) -> {
                    String account = newOrder.getAccount();
                    if (map.containsKey(account)) {
                        Order order = map.get(account);
                        map.put(account, new Order(0, account, order.getBuyAmount() + newOrder.getBuyAmount(),
                                order.getMoney().add(newOrder.getMoney())));
                    } else {
                        map.put(account, newOrder);
                    }
                }, (HashMap<String, Order> map1, HashMap<String, Order> map2) -> {} //貌似不开启并行的话 就不用实现并行计算逻辑
        );
        System.out.println("JSON.toJSONString(result) = " + JSON.toJSONString(result));

        List<String> strs = Arrays.asList("A","B","C");
        List<String> result1 = Optional.ofNullable(strs).map(List::stream).orElseGet(Stream::empty).collect(Collectors.toList());
        System.out.println("result1 = " + result1);

        strs = null;
        List<String> result2 = Optional.ofNullable(strs).map(List::stream).orElseGet(Stream::empty).collect(Collectors.toList());
        System.out.println("result2 = " + result2);
    }
}
@AllArgsConstructor
@Data
class Order{
    Integer id;
    String account;
    Integer buyAmount;
    BigDecimal money;

    public static Order getOrder(Order order1, Order order2){
        return new Order(0,order1.getAccount(),order1.getBuyAmount()+order2.getBuyAmount(),
                order1.getMoney().add(order2.getMoney()));
    }
}