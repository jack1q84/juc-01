package com.atguigu.java;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3 ; i++) {
            Thread.sleep(3000);
            new Thread(() -> {
                System.out.println("教室走了第" + Thread.currentThread().getName() +"人");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("关门走人...");
    }
}
