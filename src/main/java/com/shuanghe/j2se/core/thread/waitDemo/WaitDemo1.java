package com.shuanghe.j2se.core.thread.waitDemo;

/**
 * 计算输出其他线程锁计算的数据
 */
public class WaitDemo1 {
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        //启动计算线程 
        b.start();
        //线程A拥有b对象上的锁。线程为了调用wait()或notify()方法，
        //该线程必须是那个对象锁的拥有者 
        synchronized (b) {
            try {
                System.out.println("等待对象b完成计算。。。");
                //当前线程A等待 
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("b对象计算的总和是：" + b.total);
        }
    }
}

/**
 * 计算1+2+3 ... +100的和
 */
class ThreadB extends Thread {
    int total;

    public void run() {
        synchronized (this) {

            for (int i = 0; i < 101; i++) {
                total += i;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //（完成计算了）唤醒在此对象监视器上等待的单个线程，在本例中线程A被唤醒
            System.out.println("唤醒b对象");
            this.notify();
        }
    }
}