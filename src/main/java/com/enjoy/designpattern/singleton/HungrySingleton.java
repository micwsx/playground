package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:18 PM
 * @Version 1.0
 * 饿汉模式
 */
public class HungrySingleton {

    public final static HungrySingleton hungrySingleton = new HungrySingleton();

    public final static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();

    }
}
