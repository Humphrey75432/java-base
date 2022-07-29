package com.hhp.juc.sync;

public class PlainClazzSyncDemo {

    public static void main(String[] args) {
        PlainDemo plainDemo = new PlainDemo();
        // A线程进入后只有等到A释放锁才能让B线程执行
        new Thread(plainDemo::test1, "AA").start();
        new Thread(plainDemo::test2, "BB").start();
        // test3不是同步方法，不受sync锁影响，直接执行
        new Thread(plainDemo::test3, "CC").start();

        // 换成两个对象，结果也不尽相同
        PlainDemo plainDemo1 = new PlainDemo();
        new Thread(plainDemo1::test1, "AAA").start();

        PlainDemo plainDemo2 = new PlainDemo();
        new Thread(plainDemo2::test2, "BBB").start();

        // 代码块中的同步方法：锁定同一个对象
        new Thread(plainDemo::test4, "DD").start();
        new Thread(plainDemo::test5, "EE").start();

        // 代码块中的同步方法：锁定不同的对象
        new Thread(plainDemo::test6, "FF").start();
        new Thread(plainDemo::test7, "GG").start();
    }
}
