package j2se.core.threadTest2.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 小大力有秘密！
 * 力出奇迹！
 * <p/>
 * <p/>
 * Created by yushuanghe on 2017/03/15.
 */
public class LockTest {
    public static void main(String[] args) {
        final OutPuter outPuter = new OutPuter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        outPuter.output1("大力出奇迹！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        outPuter.output3("小力有秘密！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    static class OutPuter {

        private Lock lock = new ReentrantLock();

        public void output1(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public void output3(String name) {
            lock.lock();
            try {
                int len = name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }
}