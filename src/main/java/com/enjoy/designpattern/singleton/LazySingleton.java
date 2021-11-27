package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:16 PM
 * @Version 1.0
 *懒汉模式: 在使用单例的时候才会初始化对象。synchronized保证多线程获取时线程安全。
 * 缺点：加锁粒度太粗（整个方法加锁）
 */
public class LazySingleton {

    private static LazySingleton instance;

    public static synchronized LazySingleton getInstance(){
        if (instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }

    // cannot be created via new
    private LazySingleton(){}

    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();

    }
}
