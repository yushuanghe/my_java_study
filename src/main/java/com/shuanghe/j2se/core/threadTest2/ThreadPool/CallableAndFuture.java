package com.shuanghe.j2se.core.threadTest2.ThreadPool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yushuanghe on 2017/03/16.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(
                //泛型为返回值类型
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return "大力出奇迹！";
                    }
                }
        );
        System.out.println("等待结果");
        try {
            //java.util.concurrent.TimeoutException
            System.out.println("结果：" + future.get(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            completionService.submit(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            Thread.sleep(new Random().nextInt(5000));
                            return taskId;
                        }
                    });
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> future1 = completionService.take();
                System.out.println(future1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
