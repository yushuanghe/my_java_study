package j2se.core.thread.job.twoManTalk;

/**
 * Created by yobdc on 2016/12/21.
 */
public class WangSiTu implements Runnable {

    boolean wakeup;
    String sentence;

    public WangSiTu() {
        this.wakeup = false;
    }

    private String[] arr = {"嗯！", "你敢！", "来者可是，诸葛孔明？",
            "我太祖四方仰德", "抱大器孕大才"};

    @Override
    public void run() {
        talk();
    }

    private synchronized void talk() {
        for (int i = 0; i < 100; i++) {
            try {
                if (wakeup) {
                    if ("我从未见过如此厚颜无耻之人！".equals(sentence)) {
                        System.out.println("王司徒  卒。。。");
                    } else {
                        int index = 0;
                        System.out.println(Thread.currentThread().getName() + "，第" + (i + 1) + "次");
                        index = (int) (Math.random() * 5);
                        System.out.println("下标：" + index);
                        System.out.println(arr[index]);
                        wakeup = false;
                    }
//                    Thread.sleep(2000);

                    notify();
                    wait();
                } else {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
