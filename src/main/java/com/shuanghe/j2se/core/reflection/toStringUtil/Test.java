package com.shuanghe.j2se.core.reflection.toStringUtil;

import com.shuanghe.j2se.core.reflection.Person;

/**
 * Created by yobdc on 2017/01/13.
 */
public class Test {
    public static void main(String[] args) {
        Person person = new Person(1001, "大力", 40);
        String str = ToStringUtil.toString(person);
        System.out.println(str);
    }
}
