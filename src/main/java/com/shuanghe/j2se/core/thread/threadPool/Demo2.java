package com.shuanghe.j2se.core.thread.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java线程：线程池-
 * 延时线程池
 *
 * @author Administrator 2009-11-4 23:30:44
 */
public class Demo2 {
    public static void main(String[] args) {
        //创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThread1();
        Thread t2 = new MyThread1();
        Thread t3 = new MyThread1();
        Thread t4 = new MyThread1();
        Thread t5 = new MyThread1();
        t5.setName("t5");
        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        //使用延迟执行风格的方法
        pool.schedule(t4, 5, TimeUnit.SECONDS);
        pool.schedule(t5, 10, TimeUnit.SECONDS);
        //关闭线程池
        pool.shutdown();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}
