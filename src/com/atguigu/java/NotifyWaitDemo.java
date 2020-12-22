package com.atguigu.java;

class PrintDemo{

    private String str = "ABCDEFGHIJKLMNOPQRSTUVWYZ";

//    private  Object obj = new Object();
    
    public synchronized void printNum(){
        this.notify();
        for (int i = 1; i < 27; i++) {
            try {
                    System.out.println(Thread.currentThread().getName() + "---" + i);
                this.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                this.notify();
            }
        }
    }
    
    public synchronized void printStr(){

        this.notify();
        for (int i = 0; i < str.length(); i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "---" + str.charAt(i));
                this.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                this.notify();
            }
        }
    }

}


public class NotifyWaitDemo {
    public static void main(String[] args) {

        PrintDemo printDemo = new PrintDemo();

        Thread thread01 = new Thread(() -> {
            printDemo.printNum();
        }, "thread01");

        Thread thread02 = new Thread(() -> {
            printDemo.printStr();
        }, "thread02");

        thread01.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread02.start();
    }
}
