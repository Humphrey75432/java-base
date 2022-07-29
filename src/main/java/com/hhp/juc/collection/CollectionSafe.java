package com.hhp.juc.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionSafe {

    public static void main(String[] args) {
        // 保证集合线程安全的思路：
        // 使用同步锁（synchronized)
        // 使用动态数组，借助volatile关键字实现额外线程可见性质；
        // 借助互斥锁保护数据，拿到锁先往volatile数组写入数据，再释放锁
        // Vector
        System.out.println("=== Using vector ===");
        List<String> vector = new Vector<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                vector.add(UUID.randomUUID().toString());
                System.out.println(vector);
            }, "Thread" + i).start();
        }

        // Collections
        System.out.println("=== Using synchronizedList ===");
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                synchronizedList.add(UUID.randomUUID().toString());
                System.out.println(synchronizedList);
            }, "Thread" + i).start();
        }

        // Using CopyOnWriteArrayList
        List<String> copyWriteList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                copyWriteList.add(UUID.randomUUID().toString());
                System.out.println(copyWriteList);
            }, "Thread" + i).start();
        }
    }
}
