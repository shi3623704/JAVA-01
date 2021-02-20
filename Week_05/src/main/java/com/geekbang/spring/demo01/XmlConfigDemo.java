package com.geekbang.spring.demo01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:50
 * @Despatch
 */
public class XmlConfigDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springApplication.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
        Student peixinyi = (Student) context.getBean("shitiancheng");
        System.out.println(peixinyi);
    }
}
