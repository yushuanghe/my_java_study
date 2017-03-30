package j2se.core.thread;

/**
 * java中实现线程的第二种方式（推荐方式）
 * <p/>
 * 继承Thread类实现线程时，线程类无法继承其他类来实现一些功能，实现接口则没有这种限制
 * 实现Runnable接口的方式可以达到资源共享的效果
 * Created by yobdc on 2016/12/17.
 */
public class SecondThread {
    public static void main(String[] args) {
        HelloRuner r = new HelloRuner(100);
        Thread t1 = new Thread(r, "大力1");
        Thread t2 = new Thread(r, "大力2");
        t1.start();
        t2.start();
    }
}

class HelloRuner implements Runnable {
    private long max;

    public HelloRuner(long max) {
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
