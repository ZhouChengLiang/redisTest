package zcl20200217;


import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.stream.Stream;

/**
 * @author zhoucl
 * 
 * 2020/3/8 下午3:58
 */
public class MultiSetTest {

    public static void main(String[] args) {
        String text = "相恨不如潮有信,相爱始觉海非深,大海啊,全是水,马儿啊,四条腿,少年不知道愁滋味,为赋新词强说愁";
        Multiset<String> multiset = HashMultiset.create();
        Stream.of(text.split("")).forEach(str->multiset.add(str));

        System.out.println("multiset = " + multiset);
        System.out.println("multiset.size() = " + multiset.size());
        System.out.println("multiset.count() = " + multiset.count("相"));
        multiset.elementSet().stream().filter(word->!word.equalsIgnoreCase(",")).forEach(word->{
            System.out.println(word +" --> "+multiset.count(word));
        });
    }
}
