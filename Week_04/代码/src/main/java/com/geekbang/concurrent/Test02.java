package com.geekbang.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 14:39
 * @Despatch 02 使用CompletableFuture
 */
public class Test02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()-> new Random().nextInt());
        System.out.println(completableFuture.get());
    }
}
