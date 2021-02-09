package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 14:59
 * @Despatch 使用Lock
 */
public class Test05 {

    public static void main(String[] args) throws InterruptedException {
        Method05 method = new Method05();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        method.random();
                    }
                }
        ).start();
        System.out.println(method.getResult());
    }
}

class Method05 {

    private volatile int result;

    private static Lock lock = new ReentrantLock();

    public int getResult() throws InterruptedException {
        while (true) {
            lock.tryLock(30, TimeUnit.MILLISECONDS);
            return result;
        }
    }

    public void random() {
        lock.lock();
        try {
            result = new Random().nextInt();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Method{" +
                "result=" + result +
                '}';
    }

}
