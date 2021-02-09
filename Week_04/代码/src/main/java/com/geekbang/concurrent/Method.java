package com.geekbang.concurrent;

import java.util.Random;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 15:37
 * @Despatch
 */
public class Method {

    private volatile int result;

    public int getResult() {
        return result;
    }

    public void random() {
        result = new Random().nextInt();
    }

    @Override
    public String toString() {
        return "Method{" +
                "result=" + result +
                '}';
    }

}
