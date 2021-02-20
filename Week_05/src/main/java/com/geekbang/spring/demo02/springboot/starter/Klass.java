package com.geekbang.spring.demo02.springboot.starter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:39
 * @Despatch
 */
@Data
@NoArgsConstructor
public class Klass {
    private String name;
    List<Student> students;

    public Klass(String name,List<Student> students){
        this.name=name;
        this.students=students;
    }
    public void print(){
        System.out.println("students:"+this.students.size());
        students.forEach(Student::print);
    }
}
