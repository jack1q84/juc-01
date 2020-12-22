package com.atguigu.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("hello world");
        });
//        Void aVoid = completableFuture.get();
//        System.out.println(aVoid);

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("***************************");
            int i = 10 / 0;
            return 1024;
        });
        Thread.sleep(1000);
//
//        completableFuture1.whenComplete((r,e)->{
//
//            System.out.println("--------r: " + r);
//            System.out.println("--------e: " + e);
//
//        }).exceptionally((e) -> {
//            System.out.println("e--------" + e);
//            return 404;
//        });

    }
}
