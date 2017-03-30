package j2se.core.threadTest2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yushuanghe on 2017/03/17.
 */
public class BlockingQueueCommunication {
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

    /**
     * 同步等都放在同一个资源类中
     */
    static class Business {

        static BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        static BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

        /**
         * 匿名构造方法，调用在构造方法之前
         */ {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        public void sub1(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 10; j++) {
                System.out.println("thread1：执行" + i + "轮" + "，第" + j + "次");
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sub2(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 15; j++) {
                System.out.println("thread2：执行" + i + "轮" + "，第" + j + "次");
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
