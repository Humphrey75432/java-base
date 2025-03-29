package com.hhp.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class CartesianProductDemo {
    public static void main(String[] args) {
        String[] regions = {"东北地区", "华北地区"};
        String[] postMethods = {"一级邮寄方式", "二级邮寄方式", "三级邮寄方式"};
        String[] metrics = {"销售额", "利润"};

        List<List<String>> resultList = new ArrayList<>();
        resultList.add(List.of("地区", "邮寄方式", "指标名称"));
        resultList.add(List.of("地区", "邮寄方式", "指标名称"));
        resultList.addAll(generate(regions, postMethods, metrics));
        resultList.add(List.of("汇总","汇总","汇总"));

        for (List<String> strings : resultList) {
            System.out.println(strings);
        }
    }

    @SafeVarargs
    public static <T> List<List<T>> generate(T[]... arrays) {
        long total = Arrays.stream(arrays)
                .mapToLong(a -> a.length)
                .reduce(1, (a, b) -> a * b);

        // 初始化结果集（避免动态扩容）
        List<List<T>> result = new ArrayList<>((int) Math.min(total, Integer.MAX_VALUE));

        // 将输入数组转换为List集合的流
        Arrays.stream(arrays)
                // 递归式组合生成（核心逻辑）
                .reduce(
                        // 初始值：包含空集合的单元素流
                        Stream.of(Collections.<T>emptyList()),
                        // 组合器：将现有组合与当前数组合并
                        (accStream, array) -> accStream.flatMap(acc ->
                                Arrays.stream(array).map(element -> {
                                    // 创建新组合 = 已有组合 + 新元素
                                    List<T> newCombination = new ArrayList<>(acc);
                                    newCombination.add(element);
                                    return newCombination;
                                })
                        ),
                        // 并行流合并器（顺序流不会执行）
                        Stream::concat
                ).forEach(comb -> result.add(new ArrayList<>(comb)));

        return result;
    }
}
