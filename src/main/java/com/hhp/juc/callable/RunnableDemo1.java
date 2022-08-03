package com.hhp.juc.callable;

public class RunnableDemo1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "::通过实现Runnable来执行任务");
    }
}
