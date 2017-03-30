package j2se.core.thread.threadJoin;

/**
 * 添加join方法，join方法类似于一个插队的方法
 * 下面代码演示了，Thread One插队执行，直到Thread One
 * 执行完毕，再去执行主线程的代码
 * Created by yobdc on 2016/12/19.
 */
public class AddThreadJoin {
    public static void main(String[] args) {
        class Runner implements Runnable {
            @Override
            public void run() {
                t();
            }
        }

        Thread t = new Thread(new Runner(), "大力出奇迹！");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t();
    }

    private static void t() {
        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                String name = Thread.currentThread().getName();
                System.out.println(name + ":" + i / 100);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
