package test;

import java.util.LinkedList;

/**
 * Created by yobdc on 2017/01/10.
 */
public class Test01 {
    public static void main(String[] args) {
//        test01();
        //Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 2, Size: 1

//        test02();
        //Exception in thread "main" java.lang.NullPointerException

        int a = new Test01().count();
        System.out.println(a);

//        void.class;
    }

    public static void test01() {
        LinkedList list = new LinkedList();
        list.add("a");
        list.add(2, "b");
        String s = (String) list.get(1);
        System.out.println(s);
    }

    public static void test02() {
        String str = null;
        String strTest = new String(str);
        /**
         *     public String(String original) {
         this.value = original.value;
         this.hash = original.hash;
         }
         */
    }

    public int count() {
        try {
            return 5 / 0;
        } catch (Exception e) {
            return 2 * 3;
        } finally {
            return 3;
        }
    }

}





