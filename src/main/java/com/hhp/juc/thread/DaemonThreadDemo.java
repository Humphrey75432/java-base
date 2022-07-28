package com.hhp.juc.thread;

public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Let's do this!");
            while (true) {
                System.out.println("说我是空的，卧槽");
            }
        }, "aa");
        thread1.setDaemon(true);
        thread1.start();
        System.out.println("结束");
    }
}
