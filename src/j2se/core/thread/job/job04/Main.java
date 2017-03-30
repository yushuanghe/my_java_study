package j2se.core.thread.job.job04;

/**
 * Created by yobdc on 2016/12/23.
 */
public class Main {

    private long addNum;
    private int threadNum;

    public Main() {
        this.addNum = 100000000000l;
        this.threadNum = 5;
    }

    public Main(long addNum, int threadNum) {
        this.addNum = addNum;
        this.threadNum = threadNum;
    }

    private void start() {
        Thread thread = new Thread(new MultiThreadAddNum(addNum, threadNum));
        thread.start();
    }

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        Main main = new Main(1002l, 5);
        main.start();

        Runtime.getRuntime().addShutdownHook(
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long endTime = System.currentTimeMillis();
                        System.out.println("用时：" + (endTime - startTime) + "毫秒");
                    }
                }
                )
        );
    }
}
