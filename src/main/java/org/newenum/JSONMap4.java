package org.newenum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JSONMap4 {

    public static void main(String[] args) {
        List<VemDisplayTemplateDetailSku> vemDisplayTemplateDetailSkus = new ArrayList<>();
        VemDisplayTemplateDetailSku vemDisplayTemplateDetailSku0 = new VemDisplayTemplateDetailSku("B", "1", 1000);
        VemDisplayTemplateDetailSku vemDisplayTemplateDetailSku1 = new VemDisplayTemplateDetailSku("B", "1", 1001);
        VemDisplayTemplateDetailSku vemDisplayTemplateDetailSku2 = new VemDisplayTemplateDetailSku("B", "2", 1000);
        VemDisplayTemplateDetailSku vemDisplayTemplateDetailSku3 = new VemDisplayTemplateDetailSku("B", "3", 1001);
        VemDisplayTemplateDetailSku vemDisplayTemplateDetailSku4 = new VemDisplayTemplateDetailSku("B", "4", 1002);
        vemDisplayTemplateDetailSkus.add(vemDisplayTemplateDetailSku0);
        vemDisplayTemplateDetailSkus.add(vemDisplayTemplateDetailSku1);
        vemDisplayTemplateDetailSkus.add(vemDisplayTemplateDetailSku2);
        vemDisplayTemplateDetailSkus.add(vemDisplayTemplateDetailSku3);
        vemDisplayTemplateDetailSkus.add(vemDisplayTemplateDetailSku4);
        System.out.println(JSON.toJSONString(vemDisplayTemplateDetailSkus));

        Map<String, List<Integer>> channelNoSkuIdsMap = vemDisplayTemplateDetailSkus.stream()
                .collect(Collectors.toMap(VemDisplayTemplateDetailSku::getChannelNo, v -> {
                    List<Integer> skuIds = new ArrayList<>();
                    skuIds.add(v.getSkuId());
                    return skuIds;
                }, (List<Integer> v1, List<Integer> v2) -> {
                    v1.addAll(v2);
                    return v1;
                }));
        
        System.out.println(channelNoSkuIdsMap);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class VemDisplayTemplateDetailSku {

    private String machineSeq;

    private String channelNo;

    private Integer skuId;
}
