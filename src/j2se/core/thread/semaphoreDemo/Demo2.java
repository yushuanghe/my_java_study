package j2se.core.thread.semaphoreDemo;


import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 使用范例
 */
public class Demo2 {

    public static void main(String[] args) {

        Runnable runner = new Runnable() {
            Random ran = new Random();
            Semaphore signal = new Semaphore(3);

            public void run() {
                int time = ran.nextInt(10);
                try {
                    signal.acquire();

                    String name = Thread.currentThread().getName();
                    System.out.println(name + "正在运行，需要等待:" + time + "秒");
                    Thread.sleep(time * 1000);
                    System.out.println(name + "结束运行");

                    signal.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10; i++)
            new Thread(runner).start();
    }
}
