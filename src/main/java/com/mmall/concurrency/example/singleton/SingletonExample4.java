package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 线程不安全
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {

    }

    //instance = new SingletonExample4();执行过程
    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排
    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制        // B检测到A线程已经指向了内存
            synchronized (SingletonExample4.class) { // 同步锁
                if (instance == null) {
                    instance = new SingletonExample4(); // A线程 - 3
                }
            }
        }
        //B线程直接就获取实例了，但此时A线程还没有执行第2步
        //所以会造成线程安全问题
        return instance;
    }
}
