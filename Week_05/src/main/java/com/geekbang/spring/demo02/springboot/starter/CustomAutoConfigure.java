package com.geekbang.spring.demo02.springboot.starter;

import com.geekbang.spring.demo02.springboot.starter.properties.CustomProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 14:39
 * @Despatch
 */
@Configuration
@EnableConfigurationProperties(CustomProperties.class)
public class CustomAutoConfigure {
    @Resource
    private CustomProperties customProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "custom",value = "enabled",havingValue = "true")
    public Student buildStudent(){
        return new Student(customProperties.getAge(),customProperties.getName());
    }
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "custom",value = "enabled",havingValue = "true")
    public Klass buildKlass(){
        List<Student> students=new ArrayList();
        Optional<Object> student = Optional.ofNullable(new Student(customProperties.getAge(),customProperties.getName()));
        student.ifPresent(obj -> students.add((Student) obj));
        return new Klass(customProperties.getKlassName(),students);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "custom",value = "enabled",havingValue = "true")
    public School buildSchool(){
        List<Student> students=new ArrayList();
        Optional<Object> student = Optional.ofNullable(new Student(customProperties.getAge(),customProperties.getName()));
        student.ifPresent(obj -> students.add((Student) obj));

        List<Klass> klasses=new ArrayList();
        Optional<Object> klass = Optional.ofNullable(new Klass(customProperties.getKlassName(),students));
        klass.ifPresent(obj -> klasses.add((Klass) obj));
        return new School(customProperties.getSchoolName(),klasses);
    }
}
