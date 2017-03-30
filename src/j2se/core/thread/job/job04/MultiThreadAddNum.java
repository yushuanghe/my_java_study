package j2se.core.thread.job.job04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 累加数的线程管理类
 * Created by yobdc on 2016/12/23.
 */
public class MultiThreadAddNum implements Runnable {

    private long addNum;
    private int threadNum;

    private long[] startNums;
    private long[] endNums;
    private long[] addLengths;

    public static boolean[] isComplete;

    public static List<Long> results;

    public Lock lock;

    public MultiThreadAddNum(long addNum, int threadNum) {
        this.addNum = addNum;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {

        lock = new ReentrantLock();

        isComplete = new boolean[threadNum];
        MultiThreadAddNum.results = new ArrayList<Long>();

        //设置每个线程累加长度
        setAddLength();
        //设置每个线程累加起始数
        setStartNum();
        //设置每个线程累加结束数
        setEndNum();

        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(
                    new AddNumThread(startNums[i], endNums[i], i, lock));
            thread.start();
            isComplete[i] = false;
        }

        Thread policeThread = new Thread(new PoliceThread(threadNum));
        policeThread.start();
    }

    private void setEndNum() {

        endNums = new long[threadNum];

        for (int i = 0; i < threadNum; i++) {
            endNums[i] = startNums[i] + addLengths[i];
        }
    }

    private void setAddLength() {

        addLengths = new long[threadNum];

        long addLength = addNum / threadNum;
        for (int i = 0; i < threadNum; i++) {
            if (i == (threadNum - 1)) {
                addLengths[i] = addNum - (addLength * i);
            } else {
                addLengths[i] = addLength;
            }
        }
    }

    private void setStartNum() {

        startNums = new long[threadNum];

        long start = 0;
        for (int i = 0; i < threadNum; i++) {
            startNums[i] = start;
            start += addLengths[i];
        }
    }
}
