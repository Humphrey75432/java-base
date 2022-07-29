package com.hhp.juc.sync;

public class StaticClazzSyncDemo {

    public static void main(String[] args) {
        // 共用同一把锁，另一个线程需要等前一个线程释放才能获得
        new Thread(StaticDemo::test1, "AA").start();
        new Thread(StaticDemo::test2, "BB").start();
    }
}
