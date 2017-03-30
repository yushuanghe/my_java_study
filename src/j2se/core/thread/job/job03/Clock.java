package j2se.core.thread.job.job03;

/**
 * 任务，开启一个线程完成这个任务
 * Created by yobdc on 2016/12/21.
 */
public class Clock implements Runnable {
    private boolean wakeup;

    public Clock() {
        wakeup = false;
    }

    public boolean isWakeup() {
        return wakeup;
    }

    public void setWakeup(boolean wakeup) {
        this.wakeup = wakeup;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            wake();
        }
    }

    private synchronized void wake() {
        try {
            if (!isWakeup()) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("起床了！");
                }
                wakeup = true;

                this.notify();
                this.wait();
            } else {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
