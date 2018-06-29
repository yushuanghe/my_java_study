package com.shuanghe.j2se.core.reflection.xml;

/**
 * Created by yobdc on 2017/01/18.
 */
public class Test {
    public static void main(String[] args) {
        Activty activty = new Activty();
        activty.setContentView("src/com.shuanghe.j2se/core/reflection/xml/employee.xml");

        Corp corp = (Corp) activty.findViewById("corp001");
        System.out.println(corp);

        Employee employee1 = (Employee) activty.findViewById("emp001");
        System.out.println(employee1);
        Employee employee2 = (Employee) activty.findViewById("emp002");
        System.out.println(employee2);
        Employee employee3 = (Employee) activty.findViewById("emp003");
        System.out.println(employee3);

        Depart depart = (Depart) activty.findViewById("dep01");
        System.out.println(depart);
    }
}
