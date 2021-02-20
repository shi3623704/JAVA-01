package com.geekbang.spring.demo02.springboot.simple;

import com.geekbang.spring.demo02.springboot.starter.School;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:39
 * @Despatch
 */
@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    @Resource
    private School school;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        school.print();
    }
}
