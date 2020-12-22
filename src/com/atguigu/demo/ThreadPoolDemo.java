package com.atguigu.demo;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                5,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//        new ThreadPoolExecutor.CallerRunsPolicy()
//        new ThreadPoolExecutor.DiscardPolicy()
        new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(()-> {
                    System.out.println(Thread.currentThread().getName() + "号处理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }

    private static void ThreadMethod() {
        //        ExecutorService executorService = Executors.newFixedThreadPool(5); // 一池固定线程数
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 一池一个线程数
        ExecutorService executorService = Executors.newCachedThreadPool(); // 根据需要创建线程，线程存活60s
        try {
            for (int i = 0; i < 20; i++) {
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}
