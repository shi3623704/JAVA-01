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
public class School {
    private String name;
    List<Klass> klasses;

    public School(String name,List<Klass> klasses){
        this.klasses=klasses;
        this.name=name;
    }
    public void print(){
        System.out.println("klasses:"+this.klasses.size());
        klasses.forEach(Klass::print);
    }
}
