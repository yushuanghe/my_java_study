package com.shuanghe.j2se.core.reflection.job.biz;

import com.shuanghe.j2se.core.reflection.job.dao.IEmployee;
import com.shuanghe.j2se.core.reflection.job.entity.Employee;
import com.shuanghe.j2se.core.reflection.job.factory.Factory;

/**
 * Created by yobdc on 2017/01/18.
 */
public class EmployeeManager {
    private Factory factory;
    private IEmployee dao;

    public EmployeeManager() {
        this.factory = Factory.getInstance();
        this.dao = factory.createEmployee();
    }

    public Employee getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    public void addEmployee(Employee employee) {
        dao.addEmployee(employee);
    }
}
