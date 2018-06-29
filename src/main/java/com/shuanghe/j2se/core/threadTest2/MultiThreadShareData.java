package com.shuanghe.j2se.core.threadTest2;

/**
 * 线程共享数据
 * Created by yushuanghe on 2017/03/15.
 */
public class MultiThreadShareData {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        for (int i = 0; i < 2; i++) {
            new Thread(new Decrement(shareData)).start();
            new Thread(new Increment(shareData)).start();
        }
    }

    static class Increment implements Runnable {
        private ShareData shareData;

        Increment(ShareData shareData) {
            this.shareData = shareData;
        }

        @Override
        public void run() {
            shareData.increment();
        }
    }

    static class Decrement implements Runnable {
        private ShareData shareData;

        Decrement(ShareData shareData) {
            this.shareData = shareData;
        }

        @Override
        public void run() {
            shareData.decrement();
        }
    }

    static class ShareData {
        int j = 0;

        public synchronized void increment() {
            for (int i = 0; i < 100; i++) {
                j++;
                System.out.println(Thread.currentThread().getName() + ":" + j);
            }
        }

        public synchronized void decrement() {
            for (int i = 0; i < 100; i++) {
                j--;
                System.out.println(Thread.currentThread().getName() + ":" + j);
            }
        }
    }
}
