package com.hhp.juc.future;

import java.util.concurrent.CompletableFuture;

public class FutureDemo {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));

        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(FutureDemo::fetchPrice);
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5 + Math.random() * 20;
    }
}
