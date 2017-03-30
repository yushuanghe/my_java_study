package test;

/**
 * Created by yushuanghe on 2017/03/18.
 */
public class TestString {
    public static void main(String[] args) {
        /**
         * 编译器优化，key1和key2是同一个对象
         */
        String key1 = "abc" + "d";
        String key2 = "abc" + "d";
        System.out.println(key1 == key2);
        System.out.println(key1.equals(key2));

        key1 = new String("abc");
        key2 = new String("abc");
        System.out.println(key1 == key2);
        System.out.println(key1.equals(key2));

        /**
         * 变量，编译器不去优化，x1和x2是两个对象
         */
        String x1 = key1 + key2;
        String x2 = key1 + key2;
        System.out.println(x1 == x2);
        System.out.println(x1.equals(x2));
    }
}
