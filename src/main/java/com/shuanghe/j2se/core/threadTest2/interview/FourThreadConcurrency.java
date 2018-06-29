package com.shuanghe.j2se.core.threadTest2.interview;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yushuanghe on 2017/03/18.
 */
public class FourThreadConcurrency {
    public static void main(String[] args) {
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));

        ExecutorService service = Executors.newCachedThreadPool();
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(4);

        for (int i = 0; i < 4; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 4; i++) {
                            String log = queue.take();
                            FourThreadConcurrency.parseLog(log);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 16; i++) {
            String log = "" + (i + 1);
            {
//                FourThreadConcurrency.parseLog(log);
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        service.shutdown();
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
    }

    public static void parseLog(String log) {
        System.out.println("log:" + log + (System.currentTimeMillis() / 1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
