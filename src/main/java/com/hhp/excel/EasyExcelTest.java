package com.hhp.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class EasyExcelTest {
    public static void main(String[] args) {
        String filePath = "./hello.xlsx";
        EasyExcel.write(filePath)
                .head(buildHeader())
                .sheet()
                .doWrite(buildData());
    }

    private static List<List<String>> buildHeader() {
        List<List<String>> header = new ArrayList<>();
        header.add(List.of("地区", "邮寄方式", "指标名称")); // 列维度名称集合
        header.add(List.of("地区", "邮寄方式", "指标名称")); // 有几个行维度就重复几次，方便合并行的单元格

//        header.add(List.of("东北地区", "一级邮寄方式", "销售额")); // 每一行代表Excel的一列，如果需要在列表示层级，把表头拆开平铺
//        header.add(List.of("东北地区", "一级邮寄方式", "利润"));
//        header.add(List.of("东北地区", "二级邮寄方式", "销售额"));
//        header.add(List.of("东北地区", "二级邮寄方式", "利润"));
//        header.add(List.of("东北地区", "三级邮寄方式", "销售额"));
//        header.add(List.of("东北地区", "三级邮寄方式", "利润"));
//
//        header.add(List.of("华北地区", "一级邮寄方式", "销售额"));
//        header.add(List.of("华北地区", "一级邮寄方式", "利润"));
//        header.add(List.of("华北地区", "二级邮寄方式", "销售额"));
//        header.add(List.of("华北地区", "二级邮寄方式", "利润"));
//        header.add(List.of("华北地区", "三级邮寄方式", "销售额"));
//        header.add(List.of("华北地区", "三级邮寄方式", "利润"));

        // 平铺所有列维度值(必须确保列维度传入的顺序和SQL排列的结果一致，否则表头的层级会错乱)
        String[] regions = {"东北地区", "华北地区"};
        String[] postMethods = {"一级邮寄方式", "二级邮寄方式", "三级邮寄方式"};
        String[] metrics = {"销售额", "利润"};
        header.addAll(generate(regions, postMethods, metrics));

        header.add(List.of("汇总","汇总","汇总"));

        return header;
    }

    @SafeVarargs
    public static <T> List<List<T>> generate(T[]... arrays) {
        // 预先计算总数据量
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

    private static List<List<Object>> buildData() {
        List<List<Object>> dataList = new ArrayList<>();
        dataList.add(List.of("办公用品","电脑",0,1,2,3,4,5,6,7,8,9,10,11));
        dataList.add(List.of("办公用品","装订机",1,2,3,4,5,6,7,8,9,10,11,12));
        dataList.add(List.of("办公用品","签字笔",2,3,4,5,6,7,8,9,10,11,12,13));
        dataList.add(List.of("家具","衣柜",3,4,5,6,7,8,9,10,11,12,13,14));
        dataList.add(List.of("家具","沙发",4,5,6,7,8,9,10,11,12,13,14,15));
        dataList.add(List.of("汇总", "汇总", 0,0,0,0,0,0,0,0,0,0,0,0));
        return dataList;
    }
}
