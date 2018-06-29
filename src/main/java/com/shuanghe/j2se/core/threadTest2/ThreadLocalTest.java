package com.shuanghe.j2se.core.threadTest2;

import java.util.Random;

/**
 * private static ThreadLocal<Double> threadLocal = new ThreadLocal<>();
 * Created by yushuanghe on 2017/03/15.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Double> threadLocal = new ThreadLocal<>();
//    private static ThreadLocal<MyThreadLocalData> myThreadLocalDataThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        final Random random = new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                double data = random.nextInt() + random.nextDouble();
                System.out.println(Thread.currentThread().getName() + ":has put data:" + data);

                threadLocal.set(data);
//                MyThreadLocalData myThreadLocalData = new MyThreadLocalData();
//                myThreadLocalData.setName("大力");
//                myThreadLocalData.setAge(22);
//                myThreadLocalDataThreadLocal.set(myThreadLocalData);
                MyThreadLocalData.getThreadInstance().setName("大力");
                MyThreadLocalData.getThreadInstance().setAge(22);

                new A().get();
                new B().get();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                double data = random.nextInt();
                System.out.println(Thread.currentThread().getName() + ":has put data:" + data);

                threadLocal.set(data);
//                MyThreadLocalData myThreadLocalData = new MyThreadLocalData();
//                myThreadLocalData.setName("而立");
//                myThreadLocalData.setAge(888);
//                myThreadLocalDataThreadLocal.set(myThreadLocalData);
                MyThreadLocalData.getThreadInstance().setName("而立");
                MyThreadLocalData.getThreadInstance().setAge(888);

                new A().get();
                new B().get();
            }
        }).start();

    }

    static class A {
        public void get() {
            System.out.println("A:" + Thread.currentThread().getName() + ":has put data" + threadLocal.get());
            System.out.println("A:" + Thread.currentThread().getName() + MyThreadLocalData.getThreadInstance());
        }
    }

    static class B {
        public void get() {
            System.out.println("B:" + Thread.currentThread().getName() + ":has put data" + threadLocal.get());
            System.out.println("B:" + Thread.currentThread().getName() + MyThreadLocalData.getThreadInstance());
        }
    }
}

/**
 * 每一个线程有一个对象
 */
class MyThreadLocalData {

    //    private static MyThreadLocalData instance = null;
    private static ThreadLocal<MyThreadLocalData> map = new ThreadLocal<>();

    private MyThreadLocalData() {

    }

    public static MyThreadLocalData getThreadInstance() {
        MyThreadLocalData instance = map.get();
        if (instance == null) {
            instance = new MyThreadLocalData();
            map.set(instance);
        }
        return instance;
    }


    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyThreadLocalData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
