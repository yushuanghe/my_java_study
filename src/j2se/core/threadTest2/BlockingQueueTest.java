package j2se.core.threadTest2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yushuanghe on 2017/03/17.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        for (int i = 0; i < 2; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String data = "大力";
                        try {
                            Thread.sleep((long) (Math.random() * 10000));
                            System.out.println(Thread.currentThread().getName() + ":开始放数据");
                            queue.put(data);
                            System.out.println(Thread.currentThread().getName() + ":放数据完成，队列大小：" + queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            service.execute(runnable);
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(Thread.currentThread().getName() + ":开始取数据");
                        String data = queue.take();
                        System.out.println(Thread.currentThread().getName() + ":取数据完成，队列大小：" + queue.size() + "," + data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        service.execute(runnable);

        service.shutdown();
    }
}
