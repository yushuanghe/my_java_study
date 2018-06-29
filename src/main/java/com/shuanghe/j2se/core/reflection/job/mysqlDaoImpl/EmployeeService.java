package com.shuanghe.j2se.core.reflection.job.mysqlDaoImpl;

import com.shuanghe.j2se.core.reflection.job.dao.IEmployee;
import com.shuanghe.j2se.core.reflection.job.entity.Employee;

/**
 * Created by yobdc on 2017/01/18.
 */
public class EmployeeService implements IEmployee {
    @Override
    public Employee getEmployeeById(int id) {
        System.out.println("MYSQL:获取id为：" + id + "的员工信息");
        return new Employee();
    }

    @Override
    public void addEmployee(Employee employee) {
        System.out.println("MYSQL:添加员工成功！");
    }
}
