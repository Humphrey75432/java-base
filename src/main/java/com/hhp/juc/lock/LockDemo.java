package com.hhp.juc.lock;

public class LockDemo {

    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                share.incr();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                share.decr();
            }
        }, "BB").start();
    }
}
