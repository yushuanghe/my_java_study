package com.shuanghe.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Date: 2018/06/29
 * Time: 16:23
 *
 * @author yushuanghe
 */
public class TestList {
    /**
     * subList
     */
    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);
        list.add(i++);

        System.out.println(list);
        System.out.println(list.subList(0, 5));
        System.out.println(list.subList(5, 10));
        System.out.println(list.subList(0, list.size()));
    }
}