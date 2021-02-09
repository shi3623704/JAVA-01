package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 14:18
 * @Despatch 使用FutureTask获取值
 */
public class Test01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>((Callable) () -> new Random().nextInt());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
