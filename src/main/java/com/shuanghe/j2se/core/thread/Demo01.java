package com.shuanghe.j2se.core.thread;

import java.util.Scanner;

/**
 * Created by yobdc on 2016/12/17.
 */
public class Demo01 {
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t2 = new Thread(r, "runnable");
        t2.start();

        MyThread t1 = new MyThread();
        t1.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        long sum = 0;
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
        }
        System.out.println("sum=" + sum);
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        String str = null;
        while (!(str = input.next()).equals("quit")) {
            System.out.println(str);
        }
    }
}
