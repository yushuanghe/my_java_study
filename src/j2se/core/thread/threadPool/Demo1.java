package j2se.core.thread.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Java线程：线程池-
 */
public class Demo1 {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
//        ExecutorService pool = Executors.newFixedThreadPool(3);
        //创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
        //可以顺序执行各个任务
//        ExecutorService pool = Executors.newSingleThreadExecutor();

        //创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
        ExecutorService pool = Executors.newCachedThreadPool();

        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        // 关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}
