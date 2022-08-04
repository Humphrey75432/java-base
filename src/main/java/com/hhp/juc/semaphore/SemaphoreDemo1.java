package com.hhp.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo1 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了电脑");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("女朋友来找, " + Thread.currentThread().getName() + "离开了");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
