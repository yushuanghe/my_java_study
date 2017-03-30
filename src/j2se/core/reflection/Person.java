package j2se.core.reflection;

/**
 * Created by yobdc on 2017/01/12.
 */
public class Person {
    private static String TAG = "TAG";
    public int id = 10;
    private String name;
    private int age;

    public Person() {
        this.id = 11;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static String getTAG() {
        return TAG;
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

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int sum(int... numbers) {
        if (numbers.length == 0)
            return -1;

        int total = 0;
        for (int n : numbers)
            total += n;

        return total;
    }
}
