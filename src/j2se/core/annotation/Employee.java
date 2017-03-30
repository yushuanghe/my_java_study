package j2se.core.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Employee {

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

    @Deprecated
    public List<Employee> getAllEmployees() {
        return new ArrayList<>();
    }
}
