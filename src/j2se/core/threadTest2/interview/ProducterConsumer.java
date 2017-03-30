package j2se.core.threadTest2.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by yushuanghe on 2017/03/18.
 */
public class ProducterConsumer {
    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        final SynchronousQueue<String> queue = new SynchronousQueue<>();
        final Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(1);
                        String input = queue.take();
                        String output = TestDo.doSome(input);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
                        semaphore.release(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 10; i++) {  //这行不能改动
            String input = i + "";  //这行不能改动
//            String output = TestDo.doSome(input);
//            System.out.println(Thread.currentThread().getName() + ":" + output);
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        service.shutdown();
    }

}

//不能改动此TestDo类
class TestDo {
    public static String doSome(String input) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }
}