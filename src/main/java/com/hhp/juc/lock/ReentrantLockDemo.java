package com.hhp.juc.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("第1次获取锁，这个锁是：" + lock);
                for (int i = 2; i <= 11; i++) {
                    try {
                        lock.lock();
                        System.out.println("第"+ i + "次获取锁，这个锁是：" + lock);
                        try {
                            Thread.sleep(new Random().nextInt(200));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("这里是为了测试死锁多写一个线程");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
