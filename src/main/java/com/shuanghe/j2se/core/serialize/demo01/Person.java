package com.shuanghe.j2se.core.serialize.demo01;

import java.io.Serializable;

/**
 * Created by yobdc on 2017/01/11.
 */
public class Person implements Serializable {
    private String name;
    private int age;

    public Person() {

    }

    public Person(String str, int n) {
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
