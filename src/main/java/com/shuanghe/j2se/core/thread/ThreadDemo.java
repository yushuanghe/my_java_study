package com.shuanghe.j2se.core.thread;

import java.util.ArrayList;

public class ThreadDemo {
    /**
     * 以下这段代码将会是一个错误的代码,也就是说在ArrayList这个集合里不支持在同一线程中对ArrayList
     * 里面的数据同时读取和写入.即指ArrayList集合是线程不安全的
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        for (String element : list) {
            list.add(element + "aaa");
            System.out.println(element);
        }
    }
}


