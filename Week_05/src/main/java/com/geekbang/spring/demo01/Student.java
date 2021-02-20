package com.geekbang.spring.demo01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:45
 * @Despatch
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private int id;
    private String name;
    private String clazz;
}
