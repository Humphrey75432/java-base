package com.hhp.juc.callable;

import java.util.concurrent.Callable;

public class CallableThread<Integer> implements Callable<java.lang.Integer> {

    @Override
    public java.lang.Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "::通过实现Callable接口来执行任务，并返回结果！");
        return 1024;
    }
}
