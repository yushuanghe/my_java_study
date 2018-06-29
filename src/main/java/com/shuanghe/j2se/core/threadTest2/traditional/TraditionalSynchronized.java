package com.shuanghe.j2se.core.threadTest2.traditional;

/**
 * 小大力有秘密！
 * 力出奇迹！
 * <p/>
 * <p/>
 * Created by yushuanghe on 2017/03/15.
 */
public class TraditionalSynchronized {
    public static void main(String[] args) {
        final OutPuter outPuter = new OutPuter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        outPuter.output1("大力出奇迹！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        outPuter.output3("小力有秘密！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    static class OutPuter {

        public void output1(String name) {
            int len = name.length();
            synchronized (OutPuter.class) {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public synchronized static void output3(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
