package com.geekbang.spring.demo02.springboot.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:39
 * @Despatch
 */
@Data
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
    private int age;
    private String name;
    private String schoolName;
    private String klassName;
    private boolean enabled;
}
