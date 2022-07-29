package com.hhp.juc.sync;

public class PlainDemo {

    // 一种等价方式
    public synchronized void test1() {
        try {
            Thread.sleep(1000);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::循环第" + i + "次");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 另外一种等价写法
    public void test11() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "::循环第" + i + "次");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void test2() {
        System.out.println(Thread.currentThread().getName() + "::只循环一次的方法");
    }

    public void test3() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "::没有synchronized关键字的普通方法");
        }
    }

    // 锁定同一个对象，存在互斥关系
    public void test4() {
        synchronized (StaticDemo.class) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::测试同步代码块");
            }
        }
    }

    public void test5() {
        synchronized (StaticDemo.class) {
            System.out.println(Thread.currentThread().getName() + "::测试同步代码块");
        }
    }

    // 锁定不同对象，不存在互斥关系
    public void test6() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::测试同步代码块");
            }
        }
    }

    public void test7() {
        synchronized (PlainDemo.class) {
            System.out.println(Thread.currentThread().getName() + "::测试同步代码块");
        }
    }
}
