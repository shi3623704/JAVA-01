package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 16:46
 * @Despatch 使用Condition
 */
public class Test13 {
    public static final Lock lock = new ReentrantLock();
    public static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Method13 method = new Method13();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                method.random();
            }
        });
        t.start();
        System.out.println(method.getResult());
    }
}

class Method13 {
    int result;

    public int random() {
        Test13.lock.lock();
        try {
            result = new Random().nextInt();
            Test13.condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Test13.lock.unlock();
        }
        return result;
    }

    public int getResult() throws InterruptedException {
        Test13.lock.lock();
        try {
            if (result == 0) {
                Test13.condition.await();
            }
        } finally {
            Test13.lock.unlock();
        }
        return result;
    }
}