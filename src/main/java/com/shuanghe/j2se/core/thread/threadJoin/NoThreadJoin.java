package com.shuanghe.j2se.core.thread.threadJoin;

/**
 * 没有join方法，子线程与主线程交互执行，没有规则
 * Created by yobdc on 2016/12/19.
 */
public class NoThreadJoin {
    public static void main(String[] args) {
        class Runner implements Runnable {
            @Override
            public void run() {
                t();
            }
        }

        Thread t = new Thread(new Runner(), "大力出奇迹！");
        t.start();

        t();
    }

    private static void t() {
        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                String name = Thread.currentThread().getName();
                System.out.println(name + ":" + i / 100);
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
