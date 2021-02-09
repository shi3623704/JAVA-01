package com.geekbang.concurrent;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 16:45
 * @Despatch 使用Semaphore
 */
public class Test11 {
    private static final Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) throws InterruptedException {
        Method method = new Method();
        Thread thread = new Thread(() -> {
            try {
                semaphore.acquire(1);
                method.random();
                semaphore.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        semaphore.acquire(1);
        thread.start();
        semaphore.release(1);
        semaphore.acquire(2);
        System.out.println(method.getResult());
        semaphore.release(2);
    }
}
