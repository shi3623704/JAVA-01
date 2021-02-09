package com.geekbang.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 15:01
 * @Despatch 使用CountDownLatch
 */
public class Test08 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Method method = new Method();
        new Thread(
                () -> {
                    method.random();
                    countDownLatch.countDown();
                }
        ).start();
        System.out.println("主线程休眠1秒");
        TimeUnit.SECONDS.sleep(1);
        countDownLatch.countDown();
        System.out.println(method.getResult());
    }
}
