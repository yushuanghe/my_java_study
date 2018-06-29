package com.shuanghe.j2se.core.threadTest2.ThreadPool;

import java.util.concurrent.*;

/**
 * Created by yushuanghe on 2017/03/16.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //固定数量线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //缓存线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        //单一数量线程池，相称死亡后再来一个线程替换
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable1(i));
            //立即关掉线程池
//            threadPool.shutdownNow();
        }

        //线程池中3个线程，程序不结束
        threadPool.shutdown();

        ScheduledFuture scheduledFuture = Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        }, 5, TimeUnit.SECONDS);



//        ScheduledFuture scheduledFuture = Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("bombing!");
//            }
//        }, 10, 2, TimeUnit.SECONDS);

//        System.out.println(scheduledFuture.isDone());
    }

    static class Runnable1 implements Runnable {

        private int taskId;

        Runnable1(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " loop of " + i + " for task " + taskId);
            }
        }
    }
}
