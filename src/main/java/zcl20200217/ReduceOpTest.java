package zcl20200217;

import java.util.stream.Stream;

/**
 * @author zhoucl
 * 2020/3/8 上午10:27
 */
public class ReduceOpTest {

    public static void main(String[] args) {
        Integer result = Stream.of(1,2,3,4).reduce(9,Integer::sum);
        System.out.println("result = " + result);
    }
}
