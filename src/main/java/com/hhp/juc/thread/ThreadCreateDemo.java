package com.hhp.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCreateDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 实现Runnable接口
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + ": 通过实现Runnable接口来创建Thread线程");
        Thread thread1 = new Thread(runnable);
        thread1.start();

        // 继承Thread类
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " Using lambda method to implement thread");
        });
        thread2.start();

        // 借助FutureTask实现异步调用，并返回结果
        FutureTask<Object> futureTask = new FutureTask<>(() -> {
            System.out.println("通过实现Callable接口来创建Thread线程");
            return "这个是可以返回数据的，随便返回个什么叼东西";
        });
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        System.out.println(futureTask.get());
    }
}
