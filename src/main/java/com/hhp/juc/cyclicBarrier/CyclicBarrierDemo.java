package com.hhp.juc.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(201, () -> {
            System.out.println("恭喜你，已经抽奖20次，幸运值已满，下次抽奖必中水晶！！！");
        });

        for (int i = 1; i <= 201; i++) {
            final int count = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "抽奖一次");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        // 重置计数
        cyclicBarrier.reset();

        for (int i = 1; i <= 201; i++) {
            final int count = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "抽奖一次");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
