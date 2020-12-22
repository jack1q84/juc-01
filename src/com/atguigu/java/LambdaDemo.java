package com.atguigu.java;

@FunctionalInterface
interface Foo{

    // 抽象方法
    public int add(int x,int y);

//    public int mul(int x,int y);
    // 默认方法
    default int mul(int x,int y){
        return x * y;
    }

    default int mul01(int x,int y){
        return x * y;
    }
    // 静态方法
    public static void out(){
        System.out.println("我是静态方法");
    }
    public static void out01(){
        System.out.println("我是静态方法");
    }

}

public class LambdaDemo {
    public static void main(String[] args) {
        // 创建匿名对象
        Foo foo = (x,y)-> {
            return x + y;
        };
        // 调用
        System.out.println(foo.add(1, 2));
        System.out.println(foo.mul(2, 3));
        Foo.out();
    }

}
