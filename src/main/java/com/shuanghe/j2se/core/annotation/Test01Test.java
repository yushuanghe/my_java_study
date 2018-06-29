package com.shuanghe.j2se.core.annotation;

import java.lang.reflect.Method;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Test01Test {
    public static void main(String[] args) {
        Class<Test01> clazz = Test01.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            //判断方法中是否有指定注解类型的注解
            boolean hasAnnotation = method.isAnnotationPresent(Test.class);
            if (hasAnnotation) {
                //根据注解类型返回方法的指定类型注解
                Test annotation = method.getAnnotation(Test.class);
                System.out.println("Test01(method=" + method.getName()
                        + ",id=" + annotation.id() + ",description=" + annotation.description() + ")");
            }
        }
    }
}
