package j2se.core.threadTest2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yushuanghe on 2017/03/17.
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        final Exchanger<String> exchanger = new Exchanger<String>();

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "大力出奇迹！";
                    System.out.println(Thread.currentThread().getName() + ":把" + data1 + "交换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    //交换数据
                    String data2 = exchanger.exchange(data1);

                    System.out.println(Thread.currentThread().getName() + ":把" + data2 + "拿到");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "啊啊啊啊啊啊啊啊啊啊啊啊！";
                    System.out.println(Thread.currentThread().getName() + ":把" + data1 + "交换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    //交换数据
                    String data2 = exchanger.exchange(data1);

                    System.out.println(Thread.currentThread().getName() + ":把" + data2 + "拿到");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
