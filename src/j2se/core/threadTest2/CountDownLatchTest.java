package j2se.core.threadTest2;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yushuanghe on 2017/03/17.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":准备接收命令");
                        cdOrder.await();

                        System.out.println(Thread.currentThread().getName() + ":已接收命令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(Thread.currentThread().getName() + ":执行命令结束");
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(runnable);
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("开始！");
            cdOrder.countDown();
            System.out.println("已发送命令");
            cdAnswer.await();
            System.out.println("已收到所有响应");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }
}
