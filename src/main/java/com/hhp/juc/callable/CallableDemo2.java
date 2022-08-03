package com.hhp.juc.callable;

import java.util.concurrent.*;

public class CallableDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CallableAndFutureTask callableAndFutureTask = new CallableAndFutureTask();
        FutureTask<String> task = new FutureTask<>(callableAndFutureTask);
        new Thread(task).start();

        System.out.println("判断任务是否已经完成::" + task.isDone());
        System.out.println("阻塞式获取结果::" + task.get());
        System.out.println("在获取结果时，给定一个等待时间，如果超过等待时间还未获取结果，则主动抛出超时异常::" +
                task.get(2, TimeUnit.SECONDS));
    }
}

class CallableAndFutureTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append(i);
            Thread.sleep(100);
        }
        return str.toString();
    }
}
