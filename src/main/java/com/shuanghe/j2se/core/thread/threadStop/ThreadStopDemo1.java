package com.shuanghe.j2se.core.thread.threadStop;

/**
 * 调用Thread.Stop方法结束线程(不推荐使用)
 *
 * @author jhao
 */
public class ThreadStopDemo1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Start...");
        class Runner implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    int index = i + 1;
                    System.out.println(index + ":" + Thread.currentThread().getName() + "运行中...");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Thread t = new Thread(new Runner(), "[Thread One]");
        t.start();
        for (int i = 0; i < 100; i++) {
            int index = i + 1;
            System.out.println(index + ":" + Thread.currentThread().getName() + "运行中...");
            try {
                Thread.sleep(10);
                if (index > 10) {
                    System.out.println("准备开始结束子线程的运行...");
                    t.stop();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("子线程运行结束...");
        System.out.println("Game Over!");

    }

}
