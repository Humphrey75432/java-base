package com.hhp.merge;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EasyExcelTest {

    public static void main(String[] args) {
        File file = new File("./merge.xlsx");
        EasyExcel.write(file)
                .head(buildHeader())
                .sheet()
                .registerWriteHandler(new MergeCellStrategyHandler(true, 1, Set.of(0, 2, 4, 5)))
                .doWrite(buildData());
    }

    private static List<List<String>> buildHeader() {
        List<List<String>> head = new ArrayList<>();
        head.add(List.of("Field 1"));
        head.add(List.of("Field 2"));
        head.add(List.of("Field 3"));
        head.add(List.of("Field 4"));
        head.add(List.of("Field 5"));
        head.add(List.of("Field 6"));
        return head;
    }

    private static List<List<Object>> buildData() {
        List<List<Object>> data = new ArrayList<>();
        Set<Integer> column = Set.of(2,4,5);
        Set<Integer> row = Set.of(2,3,6,7,8);
        for (int i = 0; i < 10; i++) {
            List<Object> el = new ArrayList<>();
            if (row.contains(i)) {
                el.add("same");
                el.add("test-1");
                el.add("same");
                el.add("test-" + i);
                el.add("test-" + i);
                el.add("test-" + i);
            } else {
                for (int j = 0; j < 6; j++) {
                    if (column.contains(j)) {
                        el.add("same");
                    } else {
                        el.add("test" + (i + 1) + "-" + (j + 1));
                    }
                }
            }
            data.add(el);
        }
        return data;
    }
}
