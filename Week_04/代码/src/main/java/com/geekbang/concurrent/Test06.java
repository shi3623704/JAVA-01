package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 14:59
 * @Despatch 使用synchronized
 */
public class Test06 {
    public static void main(String[] args) throws InterruptedException {
        Method06 method = new Method06();
        new Thread(() -> {
            method.random();
        }).start();
        System.out.println(method.getResult());
    }
}

class Method06 {

    private volatile int result;

    public synchronized int getResult() throws InterruptedException {
        while (true) {
            if (result == 0) {
                wait();
            } else {
                return result;
            }
        }
    }

    public synchronized void random() {
        notifyAll();
        result = new Random().nextInt();
    }

    @Override
    public String toString() {
        return "Method{" +
                "result=" + result +
                '}';
    }

}
