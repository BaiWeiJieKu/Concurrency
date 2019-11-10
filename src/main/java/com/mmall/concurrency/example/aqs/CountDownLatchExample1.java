package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**测试CountDownLatch
 * @author Administrator
 */
@Slf4j
public class CountDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        //创建线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        //创建一个闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    //每个线程执行完毕，让计数器减一
                    countDownLatch.countDown();
                }
            });
        }
        //等待计数器减为0，才允许下面的代码继续执行
        countDownLatch.await();
        log.info("finish");
        //关闭线程池
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
