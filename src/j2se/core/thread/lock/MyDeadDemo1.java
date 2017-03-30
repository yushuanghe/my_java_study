package j2se.core.thread.lock;

/**
 * Created by yobdc on 2016/12/19.
 */
public class MyDeadDemo1 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        final String str = "大力出奇迹！";
        Thread t = new Thread() {
            @Override
            public void run() {
                synchronized (str) {
                    System.out.println("子线程开始");
                    System.out.println(str);
                    System.out.println("子线程结束");
                }
            }
        };

        t.start();

        synchronized (str) {
            System.out.println("我必须得喝大力");
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("大力出奇迹嘛！");
        }
        System.out.println("主线程结束");
    }
}
