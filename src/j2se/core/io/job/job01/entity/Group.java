package j2se.core.io.job.job01.entity;

public class Group {
    private int id;
    private String name;

    public Group() {
        super();
    }

    public Group(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

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
        return "Group [id=" + id + ", name=" + name + "]";
    }

}
