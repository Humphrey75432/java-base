package com.hhp.juc.phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {

    private static Phaser phaser = new MyPhaser();

    static class MyPhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            if (phase == 0) {
                System.out.println("所有人都到达了网吧，准备开始开黑！！");
                return false;
            } else if (phase == 1) {
                System.out.println("大家都同意，一起去次烧烤喽！！");
                return false;
            } else if (phase == 2) {
                System.out.println("大家一起回寝室！！！");
                return true;
            }
            return true;
        }
    }

    static class DoSomeThing implements Runnable {
        @Override
        public void run() {
            phaser.register();
            System.out.println(Thread.currentThread().getName() + "从家里出发，准备去学校后街上网开黑！！！");
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + "上着上着就饿了，说去吃烧烤吗？");
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + "烧烤吃完了");
            phaser.arriveAndAwaitAdvance();
        }
    }

    public static void main(String[] args) {
        DoSomeThing thing = new DoSomeThing();
        new Thread(thing, "小李").start();
        new Thread(thing, "小王").start();
        new Thread(thing, "小李").start();
    }
}
