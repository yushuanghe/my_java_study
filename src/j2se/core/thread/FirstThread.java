package j2se.core.thread;

/**
 * 第一种使用线程的例子（不推荐使用）
 * Created by yobdc on 2016/12/17.
 */
public class FirstThread {
    public static void main(String[] args) {
        HelloThread t1 = new HelloThread(100);
        HelloThread t2 = new HelloThread(100);
        //在新线程中运行
        t1.start();
        t1.setName("大力1");
        t1.setPriority(10);
        t2.start();
        t2.setName("大力2");
        //运行的线程是main线程
//        t1.run();
        System.out.println(Thread.currentThread().getName());
    }
}

class HelloThread extends Thread {
    private long max;

    public HelloThread(long max) {
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
