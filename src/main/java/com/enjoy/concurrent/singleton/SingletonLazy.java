package com.enjoy.concurrent.singleton;

/**
 * 懒汉模式:按需加载静态类并实现化对象。
 */
public class SingletonLazy {

    private static class SingletonLazyHolder {
        private static SingletonLazy instance = new SingletonLazy();
    }

    public static SingletonLazy getInstance() {
        return SingletonLazyHolder.instance;
    }

    public static void main(String[] args) {
        SingletonLazy instance=SingletonLazy.getInstance();

    }

}
