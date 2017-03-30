package j2se.core.thread.job.job03;

/**
 * Created by yobdc on 2016/12/21.
 */
public class Test {
    public static void main(String[] args) {
        Clock c = new Clock();
        Ming m = new Ming(c);

        Thread ct = new Thread(c);
        Thread mt = new Thread(m);
        mt.setDaemon(true);

        ct.start();
        mt.start();
    }
}
