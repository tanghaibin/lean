package com.haibin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haibin.tang
 * @create 2018-08-12 16:24
 **/
public class ThreadTest1 {

    static ExecutorService executorService = new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("test-thread-" + threadNumber.getAndIncrement());
            return thread;
        }
    });


    public static void main(String[] args) {
        for (int i = 0; i < 20; ++i) {
            int finalI = i;
            executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "--->>>" + finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });
        }
    }
}
