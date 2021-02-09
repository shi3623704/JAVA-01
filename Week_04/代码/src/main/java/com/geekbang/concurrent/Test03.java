package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 14:58
 * @Despatch 使用sleep
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程启动...");
        System.out.println("启动另一个线程...");
        Method method = new Method();
        new Thread(() -> {
            method.random();
            System.out.println(method);
        }).start();
        System.out.println("主线程休眠1秒，保证子线程获取到结果...");
        TimeUnit.SECONDS.sleep(1);
        int result = method.getResult();
        System.out.println(method);
        System.out.println(result);
    }
}

