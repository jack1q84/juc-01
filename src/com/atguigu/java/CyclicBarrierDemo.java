package com.atguigu.java;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("拼单成功");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println("拼单第" + Thread.currentThread().getName() + "人");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
