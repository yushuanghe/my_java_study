package com.shuanghe.j2se.core.thread.job.job04;

/**
 * Created by yobdc on 2016/12/23.
 */
public class Combination implements Runnable {
    @Override
    public void run() {

        long sum = 0;

        for (Long l : MultiThreadAddNum.results) {
            sum += l;
        }

        System.out.println("最终结果：" + sum);
    }
}
