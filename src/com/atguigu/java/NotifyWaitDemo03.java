package com.atguigu.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintDemo03{
    private String num = "A";
    private Lock lock = new ReentrantLock();
    private Condition condition01 = lock.newCondition();
    private Condition condition02 = lock.newCondition();
    private Condition condition03 = lock.newCondition();

    public void PrintA(){
        try {
            lock.lock();
            if(num != "A"){
                condition01.await();
            }
            System.out.println(num);
            num = "B";
            condition02.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void PrintB(){
        try {
            lock.lock();
            if(num != "B"){
                condition02.await();
            }
            System.out.println(num);
            num = "C";
            condition03.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void PrintC(){
        try {
            lock.lock();
            if(num != "C"){
                condition03.await();
            }
            System.out.println(num);
            num = "A";
            condition01.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

public class NotifyWaitDemo03 {
    public static void main(String[] args) {

        PrintDemo03 printDemo03 = new PrintDemo03();

        Thread thread01 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printDemo03.PrintA();
            }
        }, "thread01");

        Thread thread02 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printDemo03.PrintB();
            }
        }, "thread02");

        Thread thread03 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printDemo03.PrintC();
            }
        }, "thread03");

        thread01.start();
        thread02.start();
        thread03.start();

    }

}
