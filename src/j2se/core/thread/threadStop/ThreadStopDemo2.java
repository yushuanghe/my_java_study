package j2se.core.thread.threadStop;

/**
 * 线程停止的示例(推荐使用)
 *
 * @author jhao
 */
public class ThreadStopDemo2 {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CanStopRunner runner = new CanStopRunner();
        Thread t = new Thread(runner);
        t.start();
        Thread.sleep(1000);
        runner.stop();
    }

}

class CanStopRunner implements Runnable {

    private boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println("线程正在运行...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 改变状态标记，使线程终止
     */
    public void stop() {
        stop = true;
    }
}
