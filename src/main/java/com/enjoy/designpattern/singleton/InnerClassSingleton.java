package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:25 PM
 * @Version 1.0
 */
public class InnerClassSingleton {
    private static class SingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        InnerClassSingleton instance = InnerClassSingleton.getInstance();
    }

}
