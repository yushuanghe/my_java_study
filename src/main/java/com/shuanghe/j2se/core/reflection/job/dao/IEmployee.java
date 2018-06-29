package com.shuanghe.j2se.core.reflection.job.dao;

import com.shuanghe.j2se.core.reflection.job.entity.Employee;


/**
 * Created by yobdc on 2017/01/18.
 */
public interface IEmployee {
    Employee getEmployeeById(int id);

    void addEmployee(Employee employee);
}
