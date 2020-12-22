package com.atguigu.demo;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionDemo {
    public static void main(String[] args) {
        //
        Consumer<Integer> consumer = (c) -> {
            System.out.println("消费型函数式接口");
        };
        consumer.accept(1);
        //
        Supplier<String> supplier = () -> {
            return "供给型函数式接口";
        };
        System.out.println(supplier.get());
        //
        Function<Integer,String> function = (i) -> {
          return "功能型函数式接口";
        };
        System.out.println(function.apply(1));
        //
        Predicate<Integer> predicate = (i) -> {
          return i > 0;
        };
        System.out.println(predicate.test(1024));
    }
}
