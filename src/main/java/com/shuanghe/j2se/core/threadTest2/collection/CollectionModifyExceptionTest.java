package com.shuanghe.j2se.core.threadTest2.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList
 * Exception in thread "main" java.util.ConcurrentModificationException
 * <p/>
 * CopyOnWriteArrayList
 * 没有问题
 * Created by yushuanghe on 2017/03/17.
 */
public class CollectionModifyExceptionTest {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
//        List<User> users = new CopyOnWriteArrayList<>();
        users.add(new User("大力", 22));
        users.add(new User("而立", 33));
        users.add(new User("三力", 44));
        users.add(new User("私立", 55));
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            System.out.println("迭代循环");
            //此处抛出异常
            User user = iterator.next();
            if ("私立".equals(user.getName())) {
                users.remove(user);
            } else {
                System.out.println(user);
            }
        }
    }
}
