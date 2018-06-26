package j2se.core.lang.testFor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yushuanghe
 * Date: 2018/01/07
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 * Description:测试for循环
 */
public class TestFor {
    public static void main(String[] args) {
        TestFor main = new TestFor();
        main.test1();
        main.test2();
        main.test3();
        main.test4();
        main.test5();
        main.test6();
        main.test7();
        main.test8();
    }

    private void test1() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {
            for (int j = 0; j < 10; j++) {

            }
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("外大内小耗时：%s", endTime - startTime));
    }

    private void test2() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100000000; j++) {

            }
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("外小内大耗时：%s", endTime - startTime));
    }

    private void test3() {
        long startTime = System.nanoTime();
        int a = 1;
        int b = 2;
        for (int i = 0; i < 100000000; i++) {
            i = i * a * b;
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("未提取表达式耗时：%s", endTime - startTime));
    }

    private void test4() {
        long startTime = System.nanoTime();
        int a = 1;
        int b = 2;
        int c = a * b;
        for (int i = 0; i < 100000000; i++) {
            i = i * c;
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("已提取表达式耗时：%s", endTime - startTime));
    }

    private void test5() {
        long startTime = System.nanoTime();
        List<String> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add("");
        }

        for (int i = 0; i < list.size(); i++) {
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("每次判断list.size耗时：%s", endTime - startTime));
    }

    private void test6() {
        long startTime = System.nanoTime();
        List<String> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add("");
        }

        int a = list.size();
        for (int i = 0; i < a; i++) {
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("提取list.size耗时：%s", endTime - startTime));
    }

    private void test7() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("内部try，catch耗时：%s", endTime - startTime));
    }

    private void test8() {
        long startTime = System.nanoTime();
        try {
            for (int i = 0; i < 10000000; i++) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println(String.format("外部try，catch耗时：%s", endTime - startTime));
    }
}