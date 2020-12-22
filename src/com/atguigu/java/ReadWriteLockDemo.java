package com.atguigu.java;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private HashMap<String,String> map = new HashMap<String,String>();
    private  ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key,String value){
        try {
            readWriteLock.writeLock().lock();
            // 暂停线程
            System.out.println(Thread.currentThread().getName() + "\t正在写" + key);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t写完了" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String read(String key) {
        String result = null;
        try {
            readWriteLock.readLock().lock();
            // 暂停线程
            System.out.println(Thread.currentThread().getName() + "\t正在读" + key);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读完了" + key);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return result;
    }
}


public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.write(num + "",num + "");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.read(num + "");
            },String.valueOf(i)).start();
        }


    }
}
