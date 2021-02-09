package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 14:59
 * @Despatch 使用Future
 */
public class Test04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(() -> new Random().nextInt());
        System.out.println(submit.get());
        // 优雅关闭
        executorService.shutdown();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("shutting down")));
    }
}
