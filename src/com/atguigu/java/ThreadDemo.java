package com.atguigu.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Computer implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1 + 2;
    }
}

public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建对象
        Computer computer = new Computer();
        // 创建futureTask对象
        FutureTask<Integer> futureTask = new FutureTask<Integer>(computer);
        // 创建线程对象
        Thread thread = new Thread(futureTask);
        // 开启线程
        thread.start();
        // futureTask获取结果
        Integer result = futureTask.get();

        System.out.println(result);

    }

}
