package j2se.core.reflection.xml;

/**
 * Created by yobdc on 2017/01/18.
 */
public class Corp {
    private String id;
    private String name;

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

    @Override
    public String toString() {
        return "Corp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
