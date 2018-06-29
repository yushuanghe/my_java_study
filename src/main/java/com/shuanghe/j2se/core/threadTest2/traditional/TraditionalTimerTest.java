package com.shuanghe.j2se.core.threadTest2.traditional;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 2秒，4秒交替运行
 * Created by yushuanghe on 2017/03/15.
 */
public class TraditionalTimerTest {

    private static int x = 0;

    public static void main(String[] args) {
        /**
         * 2秒后第一次执行，以后每次4/2秒后执行
         */
        new Timer().schedule(new MyTimerTask(), 2000);

        while (true) {
            System.out.println(new Date(System.currentTimeMillis()).getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyTimerTask extends TimerTask {
        @Override
        public void run() {

            x++;
            long time = (x % 2 + 1) * 2000;

            System.out.println("boom！！！");
            new Timer().schedule(
//                        new TimerTask() {
//                            @Override
//                            public void run() {
//                                System.out.println("boom！！！");
//                            }
//                        }
                    new MyTimerTask()
                    , time);
        }
    }
}
