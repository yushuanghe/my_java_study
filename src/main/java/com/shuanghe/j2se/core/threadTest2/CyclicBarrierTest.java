package com.shuanghe.j2se.core.threadTest2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yushuanghe on 2017/03/16.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 5000));
                        System.out.println(Thread.currentThread().getName() + ":到达结合点1，已有" + (cb.getNumberWaiting() + 1) + "个线程到达");
                        cb.await();

                        Thread.sleep((long) (Math.random() * 5000));
                        System.out.println(Thread.currentThread().getName() + ":到达结合点2，已有" + (cb.getNumberWaiting() + 1) + "个线程到达");
                        cb.await();

                        Thread.sleep((long) (Math.random() * 5000));
                        System.out.println(Thread.currentThread().getName() + ":到达结合点3，已有" + (cb.getNumberWaiting() + 1) + "个线程到达");
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
