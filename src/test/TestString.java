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


        System.out.println("-----我是分割线-----");


        /**
         * StringBuffer没有实现equals和hashcode
         */
        System.out.println(new String("abc").equals(new String("abc")));
        System.out.println(new StringBuffer("abc").equals(new StringBuffer("abc")));
        System.out.println("-----我是分割线-----");

        String a = "abcd";
        String b = "ab" + "cd";
        System.out.println(a == b);//true
        String t = "cd";
        String c = "ab" + t;
        System.out.println(a == c);//false
        final String s = "cd";
        String d = "ab" + s;
        System.out.println(a == d);//true
        String r = "ab";
        String e = r + t;
        System.out.println(a == e);//false
        System.out.println(e.intern() == a);//true
        String f = new String("abcd");
        System.out.println(a == f);//false
        System.out.println(f.intern() == a);//true
        System.out.println(f.intern() == a.intern());//true
    }
}
