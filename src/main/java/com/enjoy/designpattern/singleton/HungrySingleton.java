package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:18 PM
 * @Version 1.0
 * 饿汉模式: 一般初始化单例不是很大，不会占用太多资源。一般在调用getInstance()方法之前就执行了。
 */
public class HungrySingleton {

    // Initialize the singleton object while loading the class.
    private final static HungrySingleton hungrySingleton = new HungrySingleton();
    // cannot be created via new
    private HungrySingleton(){}

    public final static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();

    }
}
