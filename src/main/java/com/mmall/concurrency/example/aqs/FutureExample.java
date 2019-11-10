package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**测试Future
 * Future表示一个可能还没有完成的异步任务的结果
 * @author Administrator
 */
@Slf4j
public class FutureExample {

    //定义一个静态内部类
    // 实现Callable（实现这个接口的类，可以在这个类中定义需要执行的方法和返回结果类型。）
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //使用线程池执行线程任务
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        //当使用线程中返回的数据的时候可以通过调用get方法获取结果
        String result = future.get();
        log.info("result：{}", result);
    }
}
