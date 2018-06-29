package com.shuanghe.j2se.core.reflection;


public class Employee {
    private static Employee employee;

    private Employee() {

    }

    private Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Employee getInstance() {
        if (employee == null)
            employee = new Employee();
        return employee;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }


}
