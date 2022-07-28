package com.hhp.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Share {

    private Integer number = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition newCondition = lock.newCondition();

    public void incr() {
        try {
            lock.lock();
            while (number != 0) {
                newCondition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            newCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decr() {
        try {
            lock.lock();
            while (number != 1) {
                newCondition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            newCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
