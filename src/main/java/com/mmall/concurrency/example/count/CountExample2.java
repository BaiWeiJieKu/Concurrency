package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**线程安全的程序计数器类
 * 使用AtomicInteger原子类
 * @author Administrator
 */
@Slf4j
@ThreadSafe
public class CountExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    //使用了java并发包下的程序计数器Atomic（原子的）
    //里面的compareAndSwapInt方法是CAS（比较并且交换）的核心
    //拿当前值（工作内存）和底层值（主内存）进行比较，如果相同（没有被其他线程修改过）才执行更改操作
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count.get());
    }

    private static void add() {
        count.incrementAndGet();
        // count.getAndIncrement();
    }
}
