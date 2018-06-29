package com.shuanghe.j2se.core.thread.lock;


/**
 * Created by yobdc on 2016/12/20.
 */
public class MyDeadLockDemo2 {
    public static void main(String[] args) {

        String s1 = "大力出奇迹！";
        String s2 = "我必须得喝大力！";

        MyThread t1 = new MyThread(s1, s2);
        MyThread t2 = new MyThread(s2, s1);

        t1.start();
        t2.start();
    }

    static class MyThread extends Thread {

        private String s1;
        private String s2;

        public MyThread(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "线程开始");
            synchronized (s1) {
                System.out.println(this.getName() + ":" + s1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s2) {
                    System.out.println(this.getName() + ":" + s2);
                }
            }
            System.out.println(this.getName() + "线程结束");
        }
    }
}
