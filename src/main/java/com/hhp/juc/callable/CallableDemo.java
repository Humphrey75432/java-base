package com.hhp.juc.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new RunnableDemo1(), "AA").start();

        FutureTask<Integer> futureTask = new FutureTask<>(new CallableThread<Integer>());
        new Thread(futureTask, "BB").start();
        System.out.println(futureTask.get());
    }
}
