package com.shuanghe.j2se.core.thread.job;

/**
 * 生产者，消费者模型
 * Created by yobdc on 2016/12/21.
 */
public class Job02 {
    public static void main(String[] args) {
        Bank bank = new Bank(30);
        Thread c1 = new Thread(new Consumer(50, bank));
        Thread c2 = new Thread(new Consumer(20, bank));
        Thread c3 = new Thread(new Consumer(30, bank));
        Thread p1 = new Thread(new Producer(10, bank));
        Thread p2 = new Thread(new Producer(10, bank));
        Thread p3 = new Thread(new Producer(10, bank));
        Thread p4 = new Thread(new Producer(10, bank));
        Thread p5 = new Thread(new Producer(10, bank));
        Thread p6 = new Thread(new Producer(10, bank));
        Thread p7 = new Thread(new Producer(20, bank));
        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}

/**
 * 仓库类
 */
class Bank {

    public static final int max_size = 100; //最大库存量
    public int curnum;     //当前库存量

    Bank() {
    }

    Bank(int curnum) {
        this.curnum = curnum;
    }

    public synchronized void produce(int num) {
        while (curnum + num > max_size) {
            try {
                //每次wait被唤醒后都需要再次判断
                wait();//将自己挂起等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        curnum += num;
        System.out.println("已经生产了" + num + "个产品，现仓储量为" + curnum);
        notifyAll();
    }

    public synchronized void consume(int num) {
        while (curnum < num) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        curnum -= num;
        System.out.println("已经消费了" + num + "个产品，现仓储量为" + curnum);
        notifyAll();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private int neednum;              //生产产品的数量
    private Bank bank;            //仓库

    public Producer(int neednum, Bank bank) {
        this.neednum = neednum;
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.produce(neednum);
    }
}

/**
 *
 */
class Consumer implements Runnable {
    private int neednum;              //生产产品的数量
    private Bank bank;            //仓库

    public Consumer(int neednum, Bank bank) {
        this.neednum = neednum;
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.consume(neednum);
    }
}
