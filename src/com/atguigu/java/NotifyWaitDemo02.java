package com.atguigu.java;

import sun.misc.ConditionLock;

import java.sql.Connection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintDemo02{

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        try {
            lock.lock();
            if (number == 1){
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName() + "------" + number);
            condition.signal();
        }finally {
            lock.unlock();
        }

    }

    public void decr() throws InterruptedException {
        try {
            lock.lock();
            if (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "------" + number);
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

}

public class NotifyWaitDemo02 {
    public static void main(String[] args) {

        PrintDemo02 printDemo02 = new PrintDemo02();

        Thread thread01 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    printDemo02.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread01");
        Thread thread02 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    printDemo02.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread02");

        thread01.start();
        thread02.start();

    }
}
