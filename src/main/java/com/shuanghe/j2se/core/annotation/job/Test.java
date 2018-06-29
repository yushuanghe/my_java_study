package com.shuanghe.j2se.core.annotation.job;

import java.lang.reflect.Method;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Test {
    public static void main(String[] args) {
        Class<Test01> clazz = Test01.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            //判断是否有注解
            boolean hasAnnotation = method.isAnnotationPresent(TestAnno.class);
            if (hasAnnotation) {
                //得到注解
                TestAnno anno = method.getAnnotation(TestAnno.class);

                System.out.println(method.getName());
                System.out.println(anno.id());
                System.out.println(anno.description());
                System.out.println("==========");
            }
        }
    }
}
