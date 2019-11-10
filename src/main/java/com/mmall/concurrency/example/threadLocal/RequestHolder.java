package com.mmall.concurrency.example.threadLocal;

/**请求持有的线程
 *存储本线程独有数据
 * @author Administrator
 */
public class RequestHolder {

    /**
     * threadlocal可以在指定线程内存储数据，数据存储以后，只有指定线程可以得到存储数据
     * ThreadLocal提供了线程内存储变量的能力，这些变量不同之处在于每一个线程读取的变量是对应的互相独立的。
     */
    private final static ThreadLocal<Long> REQUEST_HOLDER = new ThreadLocal<>();

    public static void add(Long id) {
        REQUEST_HOLDER.set(id);
    }

    public static Long getId() {
        return REQUEST_HOLDER.get();
    }

    public static void remove() {
        REQUEST_HOLDER.remove();
    }
}
