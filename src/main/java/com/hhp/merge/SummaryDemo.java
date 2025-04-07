package com.hhp.merge;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SummaryDemo {
    public static void main(String[] args) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        list1.add(Map.of("月份", "2025-01", "销售额", 12900));
        list1.add(Map.of("月份", "2025-02", "销售额", 14000));
        list1.add(Map.of("月份", "2025-03", "销售额", 15600));
        list1.add(Map.of("月份", "2025-04", "销售额", 17100));
        list1.add(Map.of("月份", "2025-05", "销售额", 18500));

        int salesAmount = list1.stream()
                .mapToInt(map -> (int) map.get("销售额"))
                .sum();
        Map<String, Object> copyMap = new LinkedHashMap<>(list1.get(0));
        for (String s : copyMap.keySet()) {
            if (s.equals("销售额")) {
                copyMap.put(s, salesAmount);
            }
            if (s.equals("月份")) {
                copyMap.put(s, "汇总");
            }
        }

        list1.add(copyMap);

        List<Map<String, Object>> list2 = new ArrayList<>();
        list2.add(Map.of("省份", "广东省", "月份", "2025-01", "销售额", 3400));
        list2.add(Map.of("省份", "广东省", "月份", "2025-02", "销售额", 3400));
        list2.add(Map.of("省份", "广东省", "月份", "2025-03", "销售额", 3900));
        list2.add(Map.of("省份", "广东省", "月份", "2025-04", "销售额", 4300));
        list2.add(Map.of("省份", "广东省", "月份", "2025-05", "销售额", 4600));
        list2.add(Map.of("省份", "湖北省", "月份", "2025-01", "销售额", 9500));
        list2.add(Map.of("省份", "湖北省", "月份", "2025-02", "销售额", 10600));
        list2.add(Map.of("省份", "湖北省", "月份", "2025-03", "销售额", 11700));
        list2.add(Map.of("省份", "湖北省", "月份", "2025-04", "销售额", 12800));
        list2.add(Map.of("省份", "湖北省", "月份", "2025-05", "销售额", 13900));

        Map<Object, List<Map<String, Object>>> provinceMap = list2.stream()
                .collect(Collectors.groupingBy(map -> map.get("省份")));
        provinceMap.forEach((province, list) -> {
            int salesAmount1 = list.stream()
                    .mapToInt(map -> (int) map.get("销售额"))
                    .sum();
            Map<String, Object> copyMap2 = new LinkedHashMap<>(list.get(0));
            for (String s : copyMap2.keySet()) {
                if (s.equals("销售额")) {
                    copyMap2.put(s, salesAmount1);
                }
                if (s.equals("月份")) {
                    copyMap2.put(s, "汇总");
                }
            }
            list2.add(copyMap2);
        });

        for (Map<String, Object> stringObjectMap : list2) {
            System.out.println(stringObjectMap);
        }
    }
}
