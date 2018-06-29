package com.shuanghe.j2se.core.reflection.decompile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yobdc on 2017/01/16.
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoaderExpand loader = new ClassLoaderExpand();

        String name =
                "com.shuanghe.j2se.core.reflection.decompile.Test";
        String className =
                "C:\\java\\workspace\\BFJAVACHP1\\bin\\com.shuanghe.j2se\\core\\reflection\\decompile\\Test";

        Class<?> clazz = loader.findClass(className, name);
        System.out.println(clazz.getName());

        try {
            Method method = clazz.getMethod("test1");
            System.out.println(method.getName());
            System.out.println("----");
            method.invoke(clazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
