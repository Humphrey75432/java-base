package com.hhp.juc.latch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        // 模拟一寝室8个人去教室上课，只有等到8个人全部都出来了才可以锁寝室门。
        // 减计数器，初始值为线程数量，每当一个线程执行完毕，计数器就减1，减到0时表示线程全部执行完毕
        CountDownLatch latch = new CountDownLatch(8);
        for (int i = 1; i <= 8; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "出去啦");
                latch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "====寝室人都已经出来了，关门向教室冲！！！====");
    }
}
