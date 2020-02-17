package org.newenum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JSONMap {
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // 设备型号组表中对应机型组合的JSON字符串
        String groupCode = "{\"A\":41,\"B\":45,\"C\":45}";
        Map<String,Integer> map = JSON.parseObject(groupCode, Map.class);
        List<Integer> machineModelIds = new ArrayList<>(map.values());
        // 计算出当前数据库中设备型号信息
        List<VemMachineModelVO> vemMachineModelVOs = getVemMachineModelVOs(machineModelIds);
        // key->设备型号ID  value ->设备型号表中对应设备型号信息
        Map<Integer,VemMachineModelVO> vemMachineModelMap = vemMachineModelVOs.stream()
                .collect(Collectors.toMap(x->x.getMachineModelId(), Function.identity(),(key1 , key2)-> key2));
        List<VemMachineModelVO> result = new ArrayList<>();
        map.forEach((machineSeq,machineModelId)->{
            VemMachineModelVO source = vemMachineModelMap.get(machineModelId);
            VemMachineModelVO target = new VemMachineModelVO();
            BeanUtils.copyProperties(source, target);
            target.setMachineSeq(machineSeq);
            result.add(target);
        });
        System.out.println(JSON.toJSONString(result));
        /**
         * [{
                "columnNo": 1,// 列数
                "machineModelId": 41, // 机型ID
                "machineSeq": "A",// 边柜号
                "rowNo": 3 // 行数
            }, {
                "columnNo": 2,// 列数
                "machineModelId": 45,// 机型ID
                "machineSeq": "B",// 边柜号
                "rowNo": 7 // 行数
            }, {
                "columnNo": 2,// 列数
                "machineModelId": 45,// 机型ID
                "machineSeq": "C",// 边柜号
                "rowNo": 7 // 行数
            }]
         */
        
    }

    private static List<VemMachineModelVO> getVemMachineModelVOs(List<Integer> machineModelIds) {
        List<VemMachineModelVO> result = new ArrayList<>(3);
        VemMachineModelVO vo1 = new VemMachineModelVO(41, null, 3, 1);
        VemMachineModelVO vo2 = new VemMachineModelVO(45, null, 7, 2);
        VemMachineModelVO vo3 = new VemMachineModelVO(45, null, 7, 2);
        result.add(vo1);
        result.add(vo2);
        result.add(vo3);
        return result;
    }
    
    
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class VemMachineModelVO{
    
    private Integer machineModelId;
    
    private String machineSeq;
    
    private Integer rowNo;
    
    private Integer columnNo;
}

