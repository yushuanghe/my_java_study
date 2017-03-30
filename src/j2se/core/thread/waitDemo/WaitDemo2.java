package j2se.core.thread.waitDemo;

/**
 * wait 和 notify 使用范例
 */
public class WaitDemo2 {

    public static void main(String[] args) {

        final String mutex = "去北京";
        // 司机线程
        final Thread driver = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("[司机]正在开车去目的地的路上...");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[司机]抵达目的地了");
                synchronized (mutex) {
                    System.out.println("[司机]叫醒乘客");
                    mutex.notify();
                }
            }
        };

        // 乘客线程
        Thread passenger = new Thread() {
            @Override
            public void run() {
                synchronized (mutex) {
                    System.out.println("[乘客]告诉司机开始出发了");
                    driver.start();
                    try {
                        System.out.println("[乘客]准备开始休息了");
                        mutex.wait();

                        System.out.println("[乘客]抵达北京，开始参观故宫!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        passenger.start();
    }

}
