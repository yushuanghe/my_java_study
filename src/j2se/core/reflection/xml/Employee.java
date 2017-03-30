package j2se.core.reflection.xml;

/**
 * Created by yobdc on 2017/01/18.
 */
public class Employee {
    private String id;
    private String name;
    private String pass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
