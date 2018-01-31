package com.rookie.demo;


import com.alibaba.fastjson.serializer.SerializerFeature;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * Created by rookie on 2018/1/30.
 */
public class JsonDemo {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1","v1");
        jsonObject.put("key2","v2");
        jsonObject.put("key3","v3");
        jsonObject.put("key4","v4");
        String jsonStr = jsonObject.toString();

        System.out.println(jsonStr);

        com.alibaba.fastjson.JSONObject jsonObject1 = new com.alibaba.fastjson.JSONObject(){
            @Override
            public String toString() {
                return toJSONString(getInnerMap(), DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteMapNullValue);
            }
        };
        jsonObject1.put("a123",1);
        jsonObject1.put("a", null);
        jsonObject1.put("ba", "null");
        jsonObject1.put("b0a", "");
        System.out.println(jsonObject1.toString());
        System.out.println(jsonObject1.toJSONString());
        System.out.println(jsonObject1.containsValue(null));
        Map<String, Object> map = jsonObject1.getInnerMap();

        String s = com.alibaba.fastjson.JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
        System.out.println(s);
    }
}
