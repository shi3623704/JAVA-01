package com.geekbang.spring.demo01;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:42
 * @Despatch
 */
import org.springframework.stereotype.Component;

@Component
public class AutowiredConfig {

    public void sayHi() {
        System.out.println("你好");
    }

}
