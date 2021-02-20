package com.geekbang.spring.demo02.springboot.starter;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:39
 * @Despatch
 */
@Data
@NoArgsConstructor
public class Student {
    private int age;
    private String name;
    public Student(int age,String name){
        this.age=age;
        this.name=name;
    }

    public  void print(){
        System.out.println("student:"+this.toString());
    }
}
