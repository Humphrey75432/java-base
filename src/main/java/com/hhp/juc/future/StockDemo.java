package com.hhp.juc.future;

import java.util.concurrent.CompletableFuture;

public class StockDemo {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(StockDemo::fetchPrice);
        cf.thenAccept((result) -> System.out.println("price: " + result));
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed");
        }
        return 5 + Math.random() * 20;
    }
}
