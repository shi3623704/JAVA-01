package com.geekbang.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 16:46
 * @Despatch 使用LockSupport
 */
public class Test12 {
    public static void main(String[] args) {

        Method method = new Method();
        Thread main = Thread.currentThread();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                method.random();
                LockSupport.unpark(main);
            }
        });
        t.start();
        if (method.getResult() == 0) {
            LockSupport.park();
        }
        System.out.println("异步计算结果为：" + method);
    }
}
