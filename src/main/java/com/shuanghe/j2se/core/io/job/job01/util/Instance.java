package com.shuanghe.j2se.core.io.job.job01.util;

/**
 * 单例模式测试类
 * Created by yobdc on 2016/12/16.
 */
public class Instance {
    public static Instance instance;

    private Instance() {

    }

    public static Instance newInstance() {
        if (instance == null)
            instance = new Instance();

        return instance;
    }
}
