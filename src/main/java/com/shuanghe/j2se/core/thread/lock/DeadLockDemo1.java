package com.shuanghe.j2se.core.thread.lock;

/**
 * 死锁案例1
 * Created by yobdc on 2016/12/19.
 */
public class DeadLockDemo1 {
    public static void main(String[] args) {

        System.out.println("主线程开始");

        final StringBuffer mutex = new StringBuffer("ABCD");

        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("子线程开始");
                synchronized (mutex) {
                    mutex.reverse();
                }
                System.out.println("子线程结束");
            }
        };
        t.start();

        synchronized (mutex) {
            System.out.println("进入同步锁");
            try {
                //
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(mutex);
        }

        System.out.println("主线程结束");
    }
}
