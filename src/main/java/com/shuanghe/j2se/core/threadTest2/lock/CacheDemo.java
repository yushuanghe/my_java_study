package com.shuanghe.j2se.core.threadTest2.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yushuanghe on 2017/03/16.
 */
public class CacheDemo {

    private volatile Map cache = new HashMap<String, Object>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }

    public Object getData(String key) {
        lock.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    //需要再次进行判断，以免其他线程已经缓存完
                    if (value == null)
                        value = "数据库中的值！";
                } finally {
                    lock.writeLock().unlock();
                }
                lock.readLock().lock();
            }
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }
}
