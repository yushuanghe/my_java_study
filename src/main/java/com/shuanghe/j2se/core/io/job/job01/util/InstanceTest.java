package com.shuanghe.j2se.core.io.job.job01.util;

/**
 * Created by yobdc on 2016/12/16.
 */
public class InstanceTest {
    public static void main(String[] args) {
        Instance i1 = Instance.newInstance();
        Instance i2 = Instance.newInstance();
        System.out.println(i1 == i2);
    }
}


