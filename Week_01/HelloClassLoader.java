package com.suanfa.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author 石天成
 * @Date 2021/1/6
 * @Time 23:05
 * @Despatch
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");
            if (Objects.isNull(helloClass)){
                System.out.println("-----------类加载失败----------");
                return;
            }
            Object hello = helloClass.newInstance();
            Method helloClassMethod = helloClass.getMethod("hello");
            helloClassMethod.invoke(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 创建并读取文件
        File file = new File("D://ideawork//Hello//Hello.xlass");
        long length = file.length();
        if (0 >= length) {
            return null;
        }
        byte[] byData = new byte[new Long(length).intValue()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int read = fileInputStream.read(byData);
            if (0>=read){
                System.out.println("------------文件读取失败----------");
                return null;
            }
            // 操作字节码
            for (int i = 0; i < byData.length; i++) {
                byData[i] = (byte) (255 - byData[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, byData, 0, byData.length);
    }
}
