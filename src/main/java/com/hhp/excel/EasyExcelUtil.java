package com.hhp.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelUtil {

    public static void main(String[] args) {
        String fileName = "./noModelWrite" + System.currentTimeMillis() + ".xlsx";
        List<List<String>> head = new ArrayList<>();
        List<String> h1 = new ArrayList<>();
        h1.add("复用头");
        h1.add("姓名");
        List<String> h2 = new ArrayList<>();
        h2.add("复用头");
        h2.add("年龄");
        head.add(h1);
        head.add(h2);

        List<List<Object>> data = new ArrayList<>();
        List<Object> d1 = new ArrayList<>();
        d1.add("张三");
        d1.add("18");
        data.add(d1);

        List<Object> d2 = new ArrayList<>();
        d2.add("李四");
        d2.add("19");
        data.add(d2);

        EasyExcel.write(fileName)
                .head(head)
                .sheet("A")
                .doWrite(data);
    }
}
