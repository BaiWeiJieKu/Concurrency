package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 线程不安全的
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        //如果此时有两个线程同时执行了下面的if判断，那两个线程都会创建一次实例对象
        //线程不安全
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
