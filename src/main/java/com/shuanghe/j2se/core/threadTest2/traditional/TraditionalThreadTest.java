package com.shuanghe.j2se.core.threadTest2.traditional;

/**
 * thread不覆盖父类run方法就去找runnable，否则执行子类的run方法
 * Created by yushuanghe on 2017/03/15.
 */
public class TraditionalThreadTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("this.getname:" + this.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName());
//                        System.out.println("this.getname:" + this.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();

        /**
         * 不覆盖父类run方法就去找runnable，否则执行子类的run方法
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        System.out.println("runnable:" + Thread.currentThread().getName());
//                        System.out.println("this.getname:" + this.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        System.out.println("thread:" + Thread.currentThread().getName());
//                        System.out.println("this.getname:" + this.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
