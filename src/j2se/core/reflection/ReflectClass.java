package j2se.core.reflection;


/**
 * Created by yobdc on 2017/01/12.
 */
public class ReflectClass {
    public static void main(String[] args) {
        //通过类来得到 Class 对象
        Class<?> clz1 = Person.class;
        System.out.println(clz1);

        //通过对象来获得 Class 对象
        Class<?> clz2 = new Person().getClass();
        System.out.println(clz2);
        Class<?> clz3 = new Person().getClass();
        System.out.println(clz1 == clz2);
        System.out.println(clz2 == clz3);

        //通过Class.forName()来获取 Class 对象
        try {
            Class<?> clz4 = Class.forName("j2se.core.reflection.Person");
            System.out.println(clz2 == clz4);
            System.out.println(clz1 == clz4);

            try {
                //创建对象
                Person p = (Person) clz4.newInstance();
                System.out.println(p);

                Class<Person> clz5 = Person.class;
                Person p2 = clz5.newInstance();
                System.out.println(p2);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
