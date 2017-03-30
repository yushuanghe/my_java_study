package j2se.core.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yobdc on 2017/01/12.
 */
public class ReflectMethod {
    public static void main(String[] args) {
        Person person = new Person(1001, "大力", 40);
        Class<Person> clz = (Class<Person>) person.getClass();

        try {
            //操作getName方法
            Method getMethod = clz.getMethod("getName");
            String name = (String) getMethod.invoke(person);
            System.out.println(name);

            //操作setName方法
            Method setMethod = clz.getMethod("setName", String.class);
            setMethod.invoke(person, "逸峰");
            System.out.println(person);

            //操作私有，setAge方法
            Method setAge = clz.getDeclaredMethod("setAge", int.class);
            setAge.setAccessible(true);
            setAge.invoke(person, 44);
            System.out.println(person);

            //操作私有，getAge方法
            Method getAge = clz.getDeclaredMethod("getAge");
            getAge.setAccessible(true);
            int age = (int) getAge.invoke(person);
            System.out.println(age);

            //操作数组类型参数 sum 方法
            Method sum = clz.getMethod("sum", int[].class);
            int a = (int) sum.invoke(person, new int[]{1, 2, 3, 4, 5});
            System.out.println(a);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
