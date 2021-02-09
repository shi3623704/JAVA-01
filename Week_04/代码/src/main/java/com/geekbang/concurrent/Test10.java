package com.geekbang.concurrent;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 15:02
 * @Despatch 使用join方法
 */
public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        Method method = new Method();
        Thread thread = new Thread(method::random);
        thread.start();
        thread.join();
        System.out.println(method.getResult());
    }
}
