package j2se.core.threadTest2.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yushuanghe on 2017/03/16.
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        q3.put(new Random().nextInt(10000));
                    }
                }
            }).start();
        }
    }

    private static class Queue3 {

        //共享数据，只能有一个线程能写
        private Object data = null;

        private ReadWriteLock rwl = new ReentrantReadWriteLock();

        public void get() {
            rwl.readLock().lock();
            System.out.println(Thread.currentThread().getName() + ":读开始");
            try {
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + ":读结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.readLock().unlock();
            }
        }

        public void put(int i) {
            rwl.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + ":写开始");
            try {
                Thread.sleep((long) (Math.random() * 1000));
                int x = 0;
                x += i;
                System.out.println(Thread.currentThread().getName() + ":" + x + ":写结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.writeLock().unlock();
            }
        }
    }
}
