package com.shuanghe.j2se.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yobdc on 2017/01/12.
 */
public class ReflectConstructor {
    public static void main(String[] args) {
//        Employee employee = new Employee();
        Employee employee = Employee.getInstance();
        employee.setId(22);
        System.out.println(employee);

        System.out.println("==============");
        Class<?> clz1 = Person.class;
        try {
            //调用无参构造函数
            Person person = (Person) clz1.newInstance();
            System.out.println(person);

            //调用有参构造函数
            Constructor<Person> con1 =
                    (Constructor<Person>) clz1.getConstructor(
                            int.class, String.class, int.class);
            person = con1.newInstance(1, "二力", 66);
            System.out.println(person);

            System.out.println("==============");
            Employee e1 = Employee.getInstance();
            Employee e2 = Employee.getInstance();
            System.out.println(e1 == e2);

            Class<Employee> clzEmp = Employee.class;
            //私有构造器，抛异常
//            employee = clzEmp.newInstance();

            Constructor<Employee> conEmp = clzEmp.getDeclaredConstructor();
            conEmp.setAccessible(true);
            employee = conEmp.newInstance();

            System.out.println(employee);

            System.out.println("==============");
            Constructor<Employee> conEmp2 = clzEmp.getDeclaredConstructor(int.class, String.class);
            conEmp2.setAccessible(true);
            employee = conEmp2.newInstance(2, "大力");

            System.out.println(employee);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
