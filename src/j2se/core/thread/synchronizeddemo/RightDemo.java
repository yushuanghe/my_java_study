package j2se.core.thread.synchronizeddemo;

/**
 * Created by yobdc on 2016/12/19.
 */
public class RightDemo {
    public static void main(String[] args) {
        SellTicket my = new SellTicket();
        Thread t1 = new Thread(my, "大力1");
        Thread t2 = new Thread(my, "大力2");
        Thread t3 = new Thread(my, "大力3");
        t1.start();
        t2.start();
        t3.start();
    }

    static class SellTicket implements Runnable { // 实现了Runnable接口
        private int ticket = 10; // 一共是10张票

        public void run() { // 覆写Thread类中的run()方法
            while (true) {
                synchronized (this) {
                    if (this.ticket > 0) {
                        try {
                            Thread.sleep(100); // 在业务操作之前，加入延迟操作
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String name = Thread.currentThread().getName();
                        System.out.println(name + "卖票 --> 剩余 =  " + --this.ticket);
                    } else {
                        break; // 票卖完了，线程退出
                    }
                }
            }
        }

        private synchronized void sell() {

        }
    }
}
