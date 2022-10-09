package com.hhp.excel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class JsonDemo {

    public static void main(String[] args) {
        String jsonStr = "{\"id\":\"AppId\",\"name\":\"织信应用\",\"type\":\"Group\",\"children\":[{\"id\":\"GroupIdA\",\"name\":\"文件夹A\",\"type\":\"Group\",\"children\":[{\"id\":\"t1\",\"name\":\"文件D\",\"type\":\"Table\",\"children\":[]},{\"id\":\"t2\",\"name\":\"文件E\",\"type\":\"Table\",\"children\":[]}]},{\"id\":\"GroupIdB\",\"name\":\"文件夹B\",\"type\":\"Group\",\"children\":[{\"id\":\"t3\",\"name\":\"文件F\",\"type\":\"Table\",\"children\":[]}]},{\"id\":\"t4\",\"name\":\"文件G\",\"type\":\"Table\",\"children\":[]},{\"id\":\"t5\",\"name\":\"文件H\",\"type\":\"Table\",\"children\":[]}]}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String baseName = jsonObject.getString("name");
        StringBuilder root = new StringBuilder(baseName);
        Map<String, Object> fileTreeMap = new TreeMap<>();
        JSONArray childrenArray = jsonObject.getJSONArray("children");
        for (int i = 0; i < childrenArray.size(); i++) {
            JSONObject childrenJsonObj = childrenArray.getJSONObject(i);
            JSONArray secChildrenJsonArray = childrenJsonObj.getJSONArray("children");
            if (secChildrenJsonArray.size() > 0) {
                for (int j = 0; j < secChildrenJsonArray.size(); j++) {
                    String level2 = childrenJsonObj.getString("name");
                    root.append(File.separator).append(level2);
                    JSONObject secChildrenJsonObj = secChildrenJsonArray.getJSONObject(j);
                    String level3 = secChildrenJsonObj.getString("name") + ".xlsx";
                    root.append(File.separator).append(level3);
                    fileTreeMap.put(secChildrenJsonObj.getString("id"), root.toString());
                    root = new StringBuilder(baseName);
                }
            } else {
                String level1 = childrenJsonObj.getString("name") + ".xlsx";
                root.append(File.separator).append(level1);
                fileTreeMap.put(childrenJsonObj.getString("id"), root.toString());
                root = new StringBuilder(baseName);
            }
        }
        System.out.println(fileTreeMap);
    }
}
