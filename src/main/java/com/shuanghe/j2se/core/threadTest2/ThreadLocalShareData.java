package com.shuanghe.j2se.core.threadTest2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by yushuanghe on 2017/03/15.
 */
public class ThreadLocalShareData {

    private static double data = 0;
    private static Map<Thread, Double> threadData = new HashMap<>();

    public static void main(String[] args) {

        final Random random = new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                data = random.nextInt() + random.nextDouble();
                System.out.println(Thread.currentThread().getName() + ":has put data:" + data);

                threadData.put(Thread.currentThread(), data);

                new A().get();
                new B().get();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                data = random.nextInt();
                System.out.println(Thread.currentThread().getName() + ":has put data:" + data);

                threadData.put(Thread.currentThread(), data);

                new A().get();
                new B().get();
            }
        }).start();

    }

    static class A {
        public void get() {
            System.out.println("A:" + Thread.currentThread().getName() + ":has put data" + threadData.get(Thread.currentThread()));
        }
    }

    static class B {
        public void get() {
            System.out.println("B:" + Thread.currentThread().getName() + ":has put data" + threadData.get(Thread.currentThread()));
        }
    }
}
