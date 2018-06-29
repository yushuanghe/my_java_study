package com.shuanghe.j2se.core.threadTest2.traditional;


/**
 * 要用到共同数据（包括同步锁）的若干个方法都放在同一个类中，这种设计体现了高类聚和程序的健壮性
 * Created by yushuanghe on 2017/03/15.
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {
        Business business = new Business();
        Thread thread1 = new Thread(new Thread1(business));
        Thread thread2 = new Thread(new Thread2(business));


        thread1.start();
        thread2.start();
    }

    static class Thread1 implements Runnable {

        private Business business;

        Thread1(Business business) {
            this.business = business;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                business.sub(i);
            }
        }
    }

    static class Thread2 implements Runnable {

        private Business business;

        Thread2(Business business) {
            this.business = business;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                business.main(i);
            }
        }
    }

    /**
     * 同步等都放在同一个资源类中
     */
    static class Business {

        private boolean bShouldSub = true;

        public void sub(int i) {
            synchronized (this) {
                while (!bShouldSub) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("thread1：执行" + i + "轮" + "，第" + j + "次");
                }
                bShouldSub = false;
                notify();
            }
        }

        public void main(int i) {
            synchronized (this) {
                while (bShouldSub) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 15; j++) {
                    System.out.println("thread2：执行" + i + "轮" + "，第" + j + "次");
                }
                bShouldSub = true;
                notify();
            }
        }
    }
}
