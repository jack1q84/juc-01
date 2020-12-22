package com.atguigu.java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{

    public int number = 100;
    public Lock lock = new ReentrantLock();
//    public void sale(){
//            // 同步代码块
//            synchronized (this){
//                if(number > 0) {
//                try {
//                    Thread.sleep(100);
//                    System.out.println(Thread.currentThread().getName() + "------售出第" + (number--) + "票，" + "还剩下" +number+  "票");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    // 同步方法
   /* public synchronized void sale() {
        if (number > 0) {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "------售出第" + (number--) + "票，" + "还剩下" + number + "票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
    public synchronized void sale() {
        lock.lock();
        try {
            if (number > 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "------售出第" + (number--) + "票，" + "还剩下" + number + "票");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
// 线程 操作 资源类
public class SaleTicket {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "thread-01");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "thread-02");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "thread-03");
        // 启动线程
        thread.start();
        thread1.start();
        thread2.start();

    }
}
