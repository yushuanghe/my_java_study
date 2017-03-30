package j2se.core.thread.job.job05;

/**
 * Created by yobdc on 2016/12/28.
 */
public class MultCalc {

    private long addNum;
    private int threadNum;

    public long[] starts;
    public long[] ends;

    public long resultResult;

    public static boolean[] isComplete;

    public MultCalc() {
        this.addNum = 1002l;
        this.threadNum = 5;
        this.starts = new long[threadNum];
        this.ends = new long[threadNum];
        resultResult = 0;
        isComplete = new boolean[threadNum];
    }

    public static void main(String[] args) {
        MultCalc multCalc = new MultCalc();
        multCalc.startUp();
    }

    private void startUp() {
        final long start = System.currentTimeMillis();

        setAttribute();

        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new AddNumThread(starts[i], ends[i], i, this));
            thread.start();
        }

        Thread policeThread = new Thread(new PoliceThread(threadNum, this));
        policeThread.start();

        synchronized (this) {
            try {
                System.out.println("进入主线程wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("sum:" + resultResult);

        Runtime.getRuntime().addShutdownHook(
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long end = System.currentTimeMillis();
                        System.out.println("总共耗时：" + (end - start) + "毫秒");
                    }
                })
        );
    }

    private void setAttribute() {
        long[] lengths = new long[threadNum];

        long length = addNum / threadNum;

        for (int i = 0; i < threadNum; i++) {
            if (i == (threadNum - 1)) {
                length = addNum - (length * i);
                lengths[i] = length;
            } else {
                lengths[i] = length;
            }
        }

        long start = 0;

        for (int i = 0; i < lengths.length; i++) {
            starts[i] = start;
            start += lengths[i];
            ends[i] = starts[i] + lengths[i];
        }

//        for (int i = 0; i < threadNum; i++) {
//            System.out.println(lengths[i]);
//            System.out.println(starts[i]);
//            System.out.println(ends[i]);
//        }
    }
}

class AddNumThread implements Runnable {

    private long start;
    private long end;
    private MultCalc multCalc;
    private int threadNo;

    public AddNumThread(long start, long end, int i, MultCalc multCalc) {
        this.start = start;
        this.end = end;
        this.multCalc = multCalc;
        this.threadNo = i;
    }

    @Override
    public void run() {

        long result = 0;

        for (long i = start + 1; i <= end; i++) {
            result += i;
        }

        synchronized (multCalc) {
            multCalc.resultResult += result;
            MultCalc.isComplete[threadNo] = true;
        }
    }
}

class PoliceThread implements Runnable {

    private int threadNum;
    private MultCalc multCalc;

    public PoliceThread(int threadNum, MultCalc multCalc) {
        this.threadNum = threadNum;
        this.multCalc = multCalc;
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
                if (MultCalc.isComplete[i])
                    allStop++;
            }

            if (allStop == (threadNum))
                break;
        }

        synchronized (multCalc) {
            System.out.println("notify");
            multCalc.notify();
        }
    }
}
