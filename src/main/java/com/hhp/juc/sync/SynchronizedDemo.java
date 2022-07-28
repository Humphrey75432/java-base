package com.hhp.juc.sync;

public class SynchronizedDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

    static class Ticket {
        private int number = 30;

        public synchronized void sale() {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + (number--) + " " + number);
            }
        }
    }
}
