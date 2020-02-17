package org.newenum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public interface TokenTools {

   

    /**
     * @Author: Lixinyu
     * @Date: 14:49 2019-05-30
     * @Description: GET请求换取token
     * @Params: [paramMap, key]
     * @Return: java.lang.String
     * @Modified By:
     */
    static String getGetToken(Map<String, Object> paramMap, String key) {
        String result;

        try {
            String source = (String) paramMap.get("tokenSource");
            if (source == null || source.equals("")) {
                throw new IllegalArgumentException("tokenSource为空");
            }
            StringBuilder params = new StringBuilder();
            Set<Map.Entry<String, Object>> paramMapSet = paramMap.entrySet();
            for (Map.Entry<String, Object> entry : paramMapSet) {
                params.append("&").append(entry.getKey()).append(":").append(entry.getValue());
            }

            result = encryptStringToToken(key, source, params);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        return result;
    }

    static String encryptStringToToken(String key, String operateBy, StringBuilder params) throws NoSuchAlgorithmException {
        String result;
        Map<String, Object> treeMap = Maps.newTreeMap();
        treeMap.put("operateBy", operateBy);
        treeMap.put("params", params.substring(1));
        treeMap.put("key", key);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(treeMap.toString().getBytes());
        byte[] byteArray = md5.digest();

        StringBuilder md5StrBuff = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & aByteArray));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
            }
        }

        result = md5StrBuff.toString().toUpperCase();
        return result;
    }
    
    public static void main(String[] args) {
        String key = "ecm";
        String deliveryOrderId="PSD123456";
        String cancelReason = null;
        String handler="";
        String tokenSource = "ecm";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("deliveryOrderId", deliveryOrderId);
        paramMap.put("cancelReason", cancelReason);
        paramMap.put("handler", handler);
        paramMap.put("tokenSource", tokenSource);
        System.out.println("token >>> "+TokenTools.getGetToken(paramMap, key));
    }
}
