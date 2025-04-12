package com.hhp.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class Summary {
    public static void main(String[] args) {
        String dataStr = "{\"summary\":[{\"商品原产地\":\"菲律宾\",\"月份\":\"2025-01\",\"销售额\":3700},{\"商品原产地\":\"中国\",\"月份\":\"2025-01\",\"销售额\":9200},{\"商品原产地\":\"哥斯达黎加\",\"月份\":\"2025-02\",\"销售额\":10000},{\"商品原产地\":\"泰国\",\"月份\":\"2025-02\",\"销售额\":4000},{\"商品原产地\":\"菲律宾\",\"月份\":\"2025-03\",\"销售额\":11300},{\"商品原产地\":\"西班牙\",\"月份\":\"2025-03\",\"销售额\":4300},{\"商品原产地\":\"泰国\",\"月份\":\"2025-04\",\"销售额\":12500},{\"商品原产地\":\"中国\",\"月份\":\"2025-04\",\"销售额\":4600},{\"商品原产地\":\"哥斯达黎加\",\"月份\":\"2025-05\",\"销售额\":4900},{\"商品原产地\":\"西班牙\",\"月份\":\"2025-05\",\"销售额\":13600},{\"销售额\":78100},{\"省份\":\"广东省\",\"商品原产地\":\"菲律宾\",\"月份\":\"2025-01\",\"销售额\":2200},{\"省份\":\"广东省\",\"商品原产地\":\"中国\",\"月份\":\"2025-01\",\"销售额\":1200},{\"省份\":\"广东省\",\"商品原产地\":\"哥斯达黎加\",\"月份\":\"2025-02\",\"销售额\":1000},{\"省份\":\"广东省\",\"商品原产地\":\"泰国\",\"月份\":\"2025-02\",\"销售额\":2400},{\"省份\":\"广东省\",\"商品原产地\":\"菲律宾\",\"月份\":\"2025-03\",\"销售额\":1300},{\"省份\":\"广东省\",\"商品原产地\":\"西班牙\",\"月份\":\"2025-03\",\"销售额\":2600},{\"省份\":\"广东省\",\"商品原产地\":\"泰国\",\"月份\":\"2025-04\",\"销售额\":1500},{\"省份\":\"广东省\",\"商品原产地\":\"中国\",\"月份\":\"2025-04\",\"销售额\":2800},{\"省份\":\"广东省\",\"商品原产地\":\"哥斯达黎加\",\"月份\":\"2025-05\",\"销售额\":3000},{\"省份\":\"广东省\",\"商品原产地\":\"西班牙\",\"月份\":\"2025-05\",\"销售额\":1600},{\"省份\":\"湖北省\",\"商品原产地\":\"菲律宾\",\"月份\":\"2025-01\",\"销售额\":1500},{\"省份\":\"湖北省\",\"商品原产地\":\"中国\",\"月份\":\"2025-01\",\"销售额\":8000},{\"省份\":\"湖北省\",\"商品原产地\":\"哥斯达黎加\",\"月份\":\"2025-02\",\"销售额\":9000},{\"省份\":\"湖北省\",\"商品原产地\":\"泰国\",\"月份\":\"2025-02\",\"销售额\":1600},{\"省份\":\"湖北省\",\"商品原产地\":\"菲律宾\",\"月份\":\"2025-03\",\"销售额\":10000},{\"省份\":\"湖北省\",\"商品原产地\":\"西班牙\",\"月份\":\"2025-03\",\"销售额\":1700},{\"省份\":\"湖北省\",\"商品原产地\":\"泰国\",\"月份\":\"2025-04\",\"销售额\":11000},{\"省份\":\"湖北省\",\"商品原产地\":\"中国\",\"月份\":\"2025-04\",\"销售额\":1800},{\"省份\":\"湖北省\",\"商品原产地\":\"哥斯达黎加\",\"月份\":\"2025-05\",\"销售额\":1900},{\"省份\":\"湖北省\",\"商品原产地\":\"西班牙\",\"月份\":\"2025-05\",\"销售额\":12000},{\"省份\":\"湖北省\",\"销售额\":58500},{\"省份\":\"广东省\",\"销售额\":19600},{\"省份\":\"广东省\",\"城市\":\"广东省/广州市\",\"销售额\":13000},{\"省份\":\"广东省\",\"城市\":\"广东省/深圳市\",\"销售额\":6600},{\"省份\":\"湖北省\",\"城市\":\"湖北省/黄石市\",\"销售额\":50000},{\"省份\":\"湖北省\",\"城市\":\"湖北省/武汉市\",\"销售额\":8500},{\"销售额\":78100}],\"metaData\":{\"colSummaryIndex\":[33,38],\"subtotalRowSummaryIndex\":[11,33],\"header\":[\"省份\",\"城市\",\"商品原产地\",\"月份\",\"销售额\"],\"月份\":[\"2025-01\",\"2025-02\",\"2025-03\",\"2025-04\",\"2025-05\"],\"商品原产地\":[\"菲律宾\",\"泰国\",\"西班牙\",\"中国\",\"哥斯达黎加\"],\"rowSummaryIndex\":[0,11]},\"isError\":false,\"dataList\":[[\"广东省\",\"广东省/广州市\",\"菲律宾\",\"2025-01\",2200],[\"广东省\",\"广东省/广州市\",\"泰国\",\"2025-02\",2400],[\"广东省\",\"广东省/广州市\",\"西班牙\",\"2025-03\",2600],[\"广东省\",\"广东省/广州市\",\"中国\",\"2025-04\",2800],[\"广东省\",\"广东省/广州市\",\"哥斯达黎加\",\"2025-05\",3000],[\"广东省\",\"广东省/深圳市\",\"中国\",\"2025-01\",1200],[\"广东省\",\"广东省/深圳市\",\"哥斯达黎加\",\"2025-02\",1000],[\"广东省\",\"广东省/深圳市\",\"菲律宾\",\"2025-03\",1300],[\"广东省\",\"广东省/深圳市\",\"泰国\",\"2025-04\",1500],[\"广东省\",\"广东省/深圳市\",\"西班牙\",\"2025-05\",1600],[\"湖北省\",\"湖北省/黄石市\",\"中国\",\"2025-01\",8000],[\"湖北省\",\"湖北省/黄石市\",\"哥斯达黎加\",\"2025-02\",9000],[\"湖北省\",\"湖北省/黄石市\",\"菲律宾\",\"2025-03\",10000],[\"湖北省\",\"湖北省/黄石市\",\"泰国\",\"2025-04\",11000],[\"湖北省\",\"湖北省/黄石市\",\"西班牙\",\"2025-05\",12000],[\"湖北省\",\"湖北省/武汉市\",\"菲律宾\",\"2025-01\",1500],[\"湖北省\",\"湖北省/武汉市\",\"泰国\",\"2025-02\",1600],[\"湖北省\",\"湖北省/武汉市\",\"西班牙\",\"2025-03\",1700],[\"湖北省\",\"湖北省/武汉市\",\"中国\",\"2025-04\",1800],[\"湖北省\",\"湖北省/武汉市\",\"哥斯达黎加\",\"2025-05\",1900]],\"count\":20}";
        List<List<Object>> dataList = JSON.parseObject(dataStr).getJSONArray("dataList").stream()
                .map(o -> (List<Object>) o)
                .collect(Collectors.toList());
        List<String> colDimensionValues1 = JSON.parseObject(dataStr).getJSONObject("metaData").getJSONArray("商品原产地").toJavaList(String.class);
        List<String> colDimensionValues2 = JSON.parseObject(dataStr).getJSONObject("metaData").getJSONArray("月份").toJavaList(String.class);
        List<List<String>> extractRules = Lists.cartesianProduct(colDimensionValues1, colDimensionValues2);

        // 读取透视表配置，获取行维度、列维度和指标的名称
        List<String> dataHeader = JSON.parseObject(dataStr).getJSONObject("metaData").getJSONArray("header").toJavaList(String.class);

        // 构建数据映射结构
        Map<String, Map<String, Map<String, Integer>>> dataMap = new LinkedHashMap<>();
        for (List<Object> data : dataList) {
            String province = (String) data.get(dataHeader.indexOf("省份"));
            String cityFull = (String) data.get(dataHeader.indexOf("城市"));
            String[] cityParts = cityFull.split("/");
            String city = cityParts.length > 1 ? cityParts[1] : cityParts[0];
            String product = (String) data.get(dataHeader.indexOf("商品原产地"));
            String month = (String) data.get(dataHeader.indexOf("月份"));
            Integer sales = ((Number) data.get(dataHeader.indexOf("销售额"))).intValue();
            String key = product + "|" + month;

            dataMap.putIfAbsent(province, new LinkedHashMap<>());
            Map<String, Map<String, Integer>> cityMap = dataMap.get(province);
            cityMap.putIfAbsent(city, new LinkedHashMap<>());
            Map<String, Integer> salesMap = cityMap.get(city);
            salesMap.put(key, sales);
        }

        // 生成结果数组
        List<List<Object>> result = new ArrayList<>();
        for (String province : dataMap.keySet()) {
            Map<String, Map<String, Integer>> cityMap = dataMap.get(province);
            for (String city : cityMap.keySet()) {
                List<Object> row = new ArrayList<>();
                row.add(province);
                row.add(city);
                Map<String, Integer> salesMap = cityMap.get(city);
                for (List<String> rule : extractRules) {
                    String key = rule.get(0) + "|" + rule.get(1);
                    Integer sales = salesMap.get(key);
                    row.add(sales);
                }
                result.add(row);
            }
        }

        // 打印结果（示例输出）
        result.forEach(row -> System.out.println(JSON.toJSONString(row)));
    }
}
