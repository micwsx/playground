package com.enjoy.concurrent.singleton;

/**
 * 饿汉模式:按需加载对象（双重检查+volatile关键字）
 */
public class SingletonEhanDoubleCheck {

    private static volatile SingletonEhanDoubleCheck instance;

    public static SingletonEhanDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonEhanDoubleCheck.class) {
                // 如果不加锁，第一个线程创建完对象释放锁后，后面进入对象也会创建对象，会产生多个对象。
                if (instance == null) {
                    instance = new SingletonEhanDoubleCheck();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonEhanDoubleCheck instance= SingletonEhanDoubleCheck.getInstance();
        System.out.println(instance);
    }
}
