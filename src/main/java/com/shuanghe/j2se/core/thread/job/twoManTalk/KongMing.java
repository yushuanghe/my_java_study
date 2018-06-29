package com.shuanghe.j2se.core.thread.job.twoManTalk;


/**
 * Created by yobdc on 2016/12/21.
 */
public class KongMing implements Runnable {

    private String[] arr = {"我从未见过如此厚颜无耻之人！", "二臣贼子，苍髯老贼！",
            "狗比！", "艹你霸霸", "我以为，你是一只大王霸", "人生天地之间，以忠孝为立身之本"};

    private WangSiTu wangSiTu;

    public KongMing(WangSiTu wangSiTu) {
        this.wangSiTu = wangSiTu;
    }

    @Override
    public void run() {
        talk();
    }

    private void talk() {
        for (int i = 0; i < 10; i++) {
            synchronized (wangSiTu) {
                try {
                    if (!wangSiTu.wakeup) {
                        int index = 0;
                        if (i < 4) {
                            do {
                                index = (int) (Math.random() * 6);
//                            System.out.println(index);
                            } while (index == 0);
                        }
                        System.out.println(Thread.currentThread().getName() + "，第" + (i + 1) + "次");

                        System.out.println("下标：" + index);
                        System.out.println(arr[index]);

//                        Thread.sleep(2000);

                        wangSiTu.wakeup = true;
                        wangSiTu.sentence = arr[index];

                        wangSiTu.notify();
                        wangSiTu.wait();
                    } else {
//                        wangSiTu.wait();
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
