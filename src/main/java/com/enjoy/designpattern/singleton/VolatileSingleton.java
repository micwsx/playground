package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:19 PM
 * @Version 1.0
 * 双重判断：
 */
public class VolatileSingleton {
    // volatile保证多线程之间变量的可见性（变量修改后，其它线程能即时更新）
    public static volatile VolatileSingleton instance;

    private VolatileSingleton(){}

    public static VolatileSingleton getInstance() {
        // synchronized放在这里就没有太大意义，很多情况下是有初始化实现，直接返回。
        // 如果没有初始化时，则做同步控制。
        if (instance == null) {
            synchronized (VolatileSingleton.class) {
                // 如果多个线程执行到这里后，前面线程已经实例，后面则不用再实例化，所以加入双重校验。
                if (instance == null) {
                    // 这个动作并不是一个原子操作。
                    // 1.加载类对象，分配内存空间
                    // 2.执行构造器
                    // 3.instance对象指向对象的内存地址
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileSingleton instance = VolatileSingleton.getInstance();

    }

}
