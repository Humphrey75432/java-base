package com.hhp.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Thread t1 = new WorkThread();
        Thread t2 = new WorkThread();
        Thread t3 = new WorkThread();
        Thread t4 = new WorkThread();

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);

        pool.shutdown();
    }
}
