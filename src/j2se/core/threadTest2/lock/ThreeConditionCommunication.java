package j2se.core.threadTest2.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 要用到共同数据（包括同步锁）的若干个方法都放在同一个类中，这种设计体现了高类聚和程序的健壮性
 * Created by yushuanghe on 2017/03/15.
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {
        Business business = new Business();
        Thread thread1 = new Thread(new Thread1(business));
        Thread thread2 = new Thread(new Thread2(business));
        Thread thread3 = new Thread(new Thread3(business));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Thread1 implements Runnable {

        private Business business;

        Thread1(Business business) {
            this.business = business;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                business.sub1(i);
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
                business.sub2(i);
            }
        }
    }

    static class Thread3 implements Runnable {

        private Business business;

        Thread3(Business business) {
            this.business = business;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                business.sub3(i);
            }
        }
    }

    /**
     * 同步等都放在同一个资源类中
     */
    static class Business {

        private Lock lock = new ReentrantLock();
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();

        private int subx = 1;

        public void sub1(int i) {
            lock.lock();
            try {
                while (subx != 1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "：执行" + i + "轮" + "，第" + j + "次");
                }
                subx = 2;

                condition2.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub2(int i) {
            lock.lock();
            try {
                while (subx != 2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 15; j++) {
                    System.out.println(Thread.currentThread().getName() + "：执行" + i + "轮" + "，第" + j + "次");
                }
                subx = 3;

                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub3(int i) {
            lock.lock();
            try {
                while (subx != 3) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 20; j++) {
                    System.out.println(Thread.currentThread().getName() + "：执行" + i + "轮" + "，第" + j + "次");
                }
                subx = 1;

                condition1.signal();
            } finally {
                lock.unlock();
            }
        }

    }
}