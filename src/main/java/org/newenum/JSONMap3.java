package org.newenum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JSONMap3 {

    public static void main(String[] args) {
        List<VemDisplayTemplateDetail> vemDisplayTemplateDetails = new ArrayList<>();
        VemDisplayTemplateDetail vemDisplayTemplateDetail0 = new VemDisplayTemplateDetail(77,"A","1");
        VemDisplayTemplateDetail vemDisplayTemplateDetail1 = new VemDisplayTemplateDetail(77,"A","2");
        VemDisplayTemplateDetail vemDisplayTemplateDetail2 = new VemDisplayTemplateDetail(77,"A","3");
        VemDisplayTemplateDetail vemDisplayTemplateDetail3 = new VemDisplayTemplateDetail(76,"B","1");
        VemDisplayTemplateDetail vemDisplayTemplateDetail4 = new VemDisplayTemplateDetail(76,"B","2");
        VemDisplayTemplateDetail vemDisplayTemplateDetail5 = new VemDisplayTemplateDetail(76,"B","3");
        VemDisplayTemplateDetail vemDisplayTemplateDetail6 = new VemDisplayTemplateDetail(76,"C","1");
        VemDisplayTemplateDetail vemDisplayTemplateDetail7 = new VemDisplayTemplateDetail(76,"C","2");
        VemDisplayTemplateDetail vemDisplayTemplateDetail8 = new VemDisplayTemplateDetail(76,"C","3");
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail0);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail1);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail2);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail3);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail4);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail5);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail6);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail7);
        vemDisplayTemplateDetails.add(vemDisplayTemplateDetail8);
        
        Map<String,List<VemDisplayTemplateDetail>> machineSeqVemDisplayTemplateDetailMap = vemDisplayTemplateDetails.stream().collect(Collectors.groupingBy(VemDisplayTemplateDetail::getMachineSeq,LinkedHashMap::new,Collectors.toList()));
        System.out.println(JSON.toJSONString(machineSeqVemDisplayTemplateDetailMap));
        
//        Map<String,Integer> machineSeqMachineModelIdMap = vemDisplayTemplateDetails.stream().collect(Collectors.toMap(k->k.getMachineSeq(), v->v.getMachineModelId(),(key1 , key2)-> key2));
//        System.out.println(machineSeqMachineModelIdMap);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class VemDisplayTemplateDetail {

    private Integer machineModelId;

    private String machineSeq;

    private String channelNo;
}
