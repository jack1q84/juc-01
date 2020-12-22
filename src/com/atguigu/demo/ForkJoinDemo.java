package com.atguigu.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class Calculator extends RecursiveTask<Integer> {
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public Calculator(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if((end - begin) <= ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        }else {
            int middle = (begin + end)/2;
            Calculator calculator01 = new Calculator(begin, middle);
            Calculator calculator02 = new Calculator(middle+1, end);
            calculator01.fork();
            calculator02.fork();
            result = calculator01.join() + calculator02.join();
        }

        return result;
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Calculator calculator = new Calculator(10, 30);

        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(calculator);

        System.out.println(forkJoinTask.get());

        forkJoinPool.shutdown();
    }
}
