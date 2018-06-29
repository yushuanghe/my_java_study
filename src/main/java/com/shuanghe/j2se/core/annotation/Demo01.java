package com.shuanghe.j2se.core.annotation;

import java.util.List;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Demo01 {
    public static void main(String[] args) {
        Employee employee = new Employee();
        List<Employee> list = employee.getAllEmployees();
    }
}
