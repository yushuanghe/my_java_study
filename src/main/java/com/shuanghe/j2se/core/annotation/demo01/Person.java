package com.shuanghe.j2se.core.annotation.demo01;

/**
 * Created by yobdc on 2017/01/17.
 */
@ClassName("学生")
public class Person {
    @FieldName(value = "姓名")
    public String name;
    @FieldName("年龄")
    public Integer age;
    @FieldName("性别")
    public Boolean sex;
    @FieldName("称谓")
    public String title;

    @Override
    public String toString() {
        return ToStringUtil.annotationToString(this);
    }

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "张学友";
        p1.age = 45;
        p1.sex = true;
        p1.title = "歌神！";

        Person p2 = new Person();
        p2.name = "刘德华";
        p2.age = 50;
        p2.sex = true;
        p2.title = "努力！";

        System.out.println(p1);
        System.out.println(p2);

        Pet t1 = new Pet();
        t1.type = "猫";
        t1.name = "咪咪";
        t1.sex = true;
        t1.age = 6;

        Pet t2 = new Pet();
        t2.type = "狗";
        t2.name = "汪汪";
        t2.sex = true;
        t2.age = 8;

        System.out.println(t1);
        System.out.println(t2);
    }
}
