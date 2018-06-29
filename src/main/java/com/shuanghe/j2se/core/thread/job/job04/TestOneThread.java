package com.shuanghe.j2se.core.thread.job.job04;

/**
 * Created by yobdc on 2016/12/21.
 */
public class TestOneThread {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i <= 1002l; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间:" + (end - start) + "毫秒");
        System.out.println("sum:" + sum);
    }
}
