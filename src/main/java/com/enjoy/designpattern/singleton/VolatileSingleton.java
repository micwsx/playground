package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:19 PM
 * @Version 1.0
 * 双从校验
 */
public class VolatileSingleton {
    public static volatile VolatileSingleton instance;

    public static VolatileSingleton getInstance() {
        // synchronized放在这里就没有太大意义，很多情况下是有初始化实现，直接返回。
        // 如果没有初始化时，则做同步控制。
        if (instance == null) {
            synchronized (VolatileSingleton.class) {
                // 如果多个线程执行到这里后，前面线程已经实例，后面则不用再实例化，所以加入双重校验。
                if (instance == null) {
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
