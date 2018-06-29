package com.shuanghe.j2se.core.thread.job.job03;

/**
 * Created by yobdc on 2016/12/21.
 */
public class Ming implements Runnable {

    private Clock c;

    public Ming(Clock c) {
        this.c = c;
    }

    public Clock getC() {
        return c;
    }

    public void setC(Clock c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            operator();
        }
    }

    private void operator() {
        synchronized (c) {
            try {
                if (c.isWakeup()) {
                    System.out.println("知道了！ 知道了！");
                    c.setWakeup(false);
                    Thread.sleep(3000);

                    c.notify();
                    c.wait();
                } else {
                    c.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
