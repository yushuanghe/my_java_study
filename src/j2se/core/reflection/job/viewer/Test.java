package j2se.core.reflection.job.viewer;


import j2se.core.reflection.job.biz.DepartManager;
import j2se.core.reflection.job.biz.EmployeeManager;
import j2se.core.reflection.job.entity.Depart;
import j2se.core.reflection.job.entity.Employee;

import java.util.List;

/**
 * Created by yobdc on 2017/01/16.
 */
public class Test {
    public static void main(String[] args) {
//        DepartManager departManager = new DepartManager(AppType.mssql);
        DepartManager departManager = new DepartManager();

        System.out.println("获取部门编号为1的部门信息");
        Depart depart = departManager.getDepartById(1);

        System.out.println("获取部门的所有部门信息");
        List<Depart> list = departManager.getAllDepart();

        EmployeeManager employeeManager = new EmployeeManager();

        System.out.println("获取员工编号为1的员工信息");
        Employee employee = employeeManager.getEmployeeById(1);
        System.out.println(employee);

        System.out.println("添加员工");
        employeeManager.addEmployee(new Employee());
    }
}
