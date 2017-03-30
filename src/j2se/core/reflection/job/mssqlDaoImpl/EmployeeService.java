package j2se.core.reflection.job.mssqlDaoImpl;

import j2se.core.reflection.job.dao.IEmployee;
import j2se.core.reflection.job.entity.Employee;

/**
 * Created by yobdc on 2017/01/18.
 */
public class EmployeeService implements IEmployee {
    @Override
    public Employee getEmployeeById(int id) {
        System.out.println("MSSQL:获取id为：" + id + "的员工信息");
        return new Employee();
    }

    @Override
    public void addEmployee(Employee employee) {
        System.out.println("MSSQL:添加员工成功！");
    }
}
