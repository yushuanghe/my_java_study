package com.shuanghe.j2se.core.annotation.job;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Test01 {
    @TestAnno(id = 1, description = "01大力出奇迹！")
    public void method01() {

    }

    @TestAnno(id = 2)
    public void method02() {

    }

    @TestAnno(id = 3, description = "03哈哈哈！")
    public void method03() {

    }
}
