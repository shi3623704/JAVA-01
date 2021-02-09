package com.geekbang.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 15:01
 * @Despatch 使用ForkJoinPool
 */
public class Test09 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Method method = new Method();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                method.random();
                return method.getResult();
            }
        });
        Integer integer = submit.get();
        System.out.println(integer);
    }
}
