package com.shuanghe.j2se.core.thread.job.job04;

import java.util.concurrent.locks.Lock;

/**
 * 累加数 worker 线程
 * Created by yobdc on 2016/12/23.
 */
public class AddNumThread implements Runnable {

    private long startNum;
    private long endNum;
    private int currentThread;
    private Lock lock;

    public AddNumThread(long startNum, long endNum, int currentThread, Lock lock) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.currentThread = currentThread;
        this.lock = lock;
    }

    @Override
    public void run() {

        long sum = 0;

        for (long i = startNum + 1; i <= endNum; i++) {
            sum += i;
        }

        synchronized (lock) {
            MultiThreadAddNum.results.add(sum);
        }

        MultiThreadAddNum.isComplete[currentThread] = true;
    }
}
