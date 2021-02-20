package com.geekbang.spring.demo01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:41
 * @Despatch
 */
public class AnnotationsConfigDemo {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.geekbang.spring");
        Student peixinyi = (Student) context.getBean("student");
        System.out.println(peixinyi);
    }
}
