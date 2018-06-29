package com.shuanghe.j2se.core.thread.job;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 夫妻取钱
 * Created by yobdc on 2016/12/21.
 */
public class Job01 {
    public static void main(String[] args) {
        new Job01().run();
    }

    public void run() {
        Family f = new Family();
        new Thread(f, "大力").start();
        new Thread(f, "二力").start();

        while (true) {
            if (f.times >= 2) {
                f.show();
                break;
            }
//            else {
//                System.out.println(f.times);
//            }
        }
    }

    class Family implements Runnable {

        private int saveMoney;
        private int getMoney;
        private int curMoney;
        private int times;

        //
        Lock lock = new ReentrantLock();

        public Family() {
            this.saveMoney = 10000;
            this.getMoney = 2000;
            this.curMoney = 0;
            this.times = 0;
        }

        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "在取钱");
            if (saveMoney >= getMoney) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                curMoney += getMoney;
                saveMoney -= getMoney;
                times++;
                System.out.println(times);
            }
            lock.unlock();
        }

        public void show() {
            System.out.println("第" + times + "次取钱，存款剩余：" + saveMoney + ",取到：" + curMoney);
        }
    }
}


