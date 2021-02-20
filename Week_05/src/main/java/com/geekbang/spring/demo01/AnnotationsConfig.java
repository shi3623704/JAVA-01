package com.geekbang.spring.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:39
 * @Despatch
 */
@Component
public class AnnotationsConfig {

    @Bean
    public Student student() {
        return new Student(1, "shitiancheng", "1班");
    }
}
