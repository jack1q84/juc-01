package com.atguigu.java;

class PrintDemo01{

    private int number = 0;

    public void incr() throws InterruptedException {
        synchronized (this){
            while(number == 1){
                wait();
            }
            System.out.println(Thread.currentThread().getName() + "-------"+ (number++));;
            notify();
        }
    }

    public void decr() throws InterruptedException {
        synchronized (this){
            while(number == 0){
                wait();
            }
            System.out.println(Thread.currentThread().getName() + "-------"+ (number--));
            notify();
        }
    }


}


public class NotifyWaitDemo01 {
    public static void main(String[] args) {
        PrintDemo01 printDemo01 = new PrintDemo01();
        Thread thread01 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    printDemo01.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-01");
        Thread thread02 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    printDemo01.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-02");
        Thread thread03 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    printDemo01.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-03");
        Thread thread04 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    printDemo01.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-04");

        thread01.start();
        thread02.start();
        thread03.start();
        thread04.start();
    }
}
