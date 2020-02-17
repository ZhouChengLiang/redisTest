package org.zcl.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

public class ChineseNameSort {
    public static void main(String[] args) {
//        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
////        String[] newArray = { "中山", "汕头", "广州", "安庆", "阳江", "南京", "武汉", "北京", "安阳", "北方" };
//        String[] newArray = {"周一","吕晋","方强","草民","阿尔卑斯","阿大"};
//        List<String> list = Arrays.asList(newArray);
//        Collections.sort(list, com);
//        System.out.println(list);
        
        List<Integer> list0 = new ArrayList<>(Arrays.asList(1,2,3,4));
        List<Integer> list1 = new ArrayList<>(Arrays.asList(2,1,4,3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2,1,3));
        
        System.out.println(CollectionUtils.isEqualCollection(list0, list1));
        System.out.println(CollectionUtils.isEqualCollection(list0, list2));
        System.out.println(CollectionUtils.isEqualCollection(list1, list2));
        System.out.println("============================================================");
        int logicalChannelNo = 1;
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 41);
        map.put("B", 42);
        map.put("C", 45);
        Map<Integer, Integer> modelIdChannelNosMap = new HashMap<>();
        modelIdChannelNosMap.put(41, 1);
        modelIdChannelNosMap.put(42, 2);
        modelIdChannelNosMap.put(45, 3);
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String machineSeq = entry.getKey();
            Integer modelId = entry.getValue();
            int size = modelIdChannelNosMap.get(modelId);
            for (int j = 0; j < size; j++) {
                System.out.println("machineSeq >>> "+machineSeq+";channelNo >>> "+(j+1)+" logicalChannelNo >>>"+String.valueOf(logicalChannelNo++));
            } 
        }
        
    }
}
