package j2se.core.thread.yield;

import java.util.Date;

/**
 * yield方法demo
 * Created by yobdc on 2016/12/19.
 */
public class YieldDemo {
    public static void main(String[] args) {
        Runner r = new Runner(true);
        Thread t1 = new Thread(r, "大力1");
        Thread t2 = new Thread(new Runner(true), "大力2");

        t2.start();
        t1.start();
    }

    private static class Runner implements Runnable {

        private boolean yield;

        public Runner(boolean yield) {
            this.yield = yield;
        }

        @Override
        public void run() {
            Date start = new Date();
            String name = Thread.currentThread().getName();
            System.out.println(name + "进入。。。");
            for (int i = 0; i < 100; i++) {
                if (yield)
                    //主动放弃已经获取的执行机会，进入runnable状态
                    Thread.yield();
                if (i % 10 == 0)
                    System.out.println(name + ":" + i / 10);
            }
            Date end = new Date();
            System.out.println(name + "共耗时：" + (end.getTime() - start.getTime()));
        }
    }
}
