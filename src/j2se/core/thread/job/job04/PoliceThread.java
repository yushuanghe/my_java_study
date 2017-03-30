package j2se.core.thread.job.job04;

/**
 * Created by yobdc on 2016/12/23.
 */
public class PoliceThread implements Runnable {

    private int threadNum;

    public PoliceThread(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        boolean isRun = true;
        while (isRun) {
            int allStop = 0;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < threadNum; i++) {
                if (MultiThreadAddNum.isComplete[i])
                    allStop += 1;
            }

            if (allStop == threadNum) {
                isRun = false;
            }
        }

        Thread thread = new Thread(new Combination());
        thread.start();
    }
}
