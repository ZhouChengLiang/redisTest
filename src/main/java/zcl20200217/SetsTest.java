package zcl20200217;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author zhoucl
 * 2020/3/8 下午4:25
 */
public class SetsTest {
    public static void main(String[] args) {
        Set<Integer> set1 = Sets.newHashSet(1,2,3,4);
        Set<Integer> set2 = Sets.newHashSet(3,4,5,6);

        // 交集
        System.out.println(Sets.intersection(set1,set2));

        // 差集
        System.out.println(Sets.difference(set1,set2));

        // 两个集合相互没有的并集
        System.out.println(Sets.symmetricDifference(set1,set2));

        // 并集
        System.out.println(Sets.union(set1,set2));

        // 该集合的所有子集
        System.out.println(JSON.toJSONString(Sets.powerSet(set1)));
    }
}
