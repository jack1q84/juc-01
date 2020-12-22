package com.atguigu.java;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeDemo {

    public static void main(String[] args) {

//        HashMap<String, String> map = new HashMap<>();
//        Hashtable<String, String> map = new Hashtable<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0,8),Thread.currentThread().getName());
                System.out.println(map);
            }).start();
        }

    }

    private static void notSafeSet(){
        //        HashSet<String> hashSet01 = new HashSet<>();
//        Set<String> hashSet = Collections.synchronizedSet(hashSet01);
        CopyOnWriteArraySet<String> hashSet = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hashSet.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(hashSet);
            }).start();
        }
    }

    private static void notSafeList() {
        ArrayList<String> list01 = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
//        Vector<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(list01);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(UUID.randomUUID().toString());
                    System.out.println(list);
            }).start();
        }
    }
}
