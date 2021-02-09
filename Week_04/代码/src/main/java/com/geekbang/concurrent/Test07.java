package com.geekbang.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author 石天成
 * @Date 2021/2/9
 * @Time 15:00
 * @Despatch 使用CyclicBarrier
 */
public class Test07 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Method method = new Method();
        new Thread(
                () -> {
                    method.random();
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
        cyclicBarrier.await();
        System.out.println("输出");
        System.out.println(method.getResult());
    }
}
