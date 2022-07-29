package com.hhp.juc.sync;

public class StaticDemo {

    public static synchronized void test1() {
        try {
            Thread.sleep(1000);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::循环第" + i + "次");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void test2() {
        System.out.println(Thread.currentThread().getName() + "::只循环一次的方法");
    }
}
