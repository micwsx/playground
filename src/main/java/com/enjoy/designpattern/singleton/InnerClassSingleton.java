package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:25 PM
 * @Version 1.0
 * 静态内部类实现单例：在执行getInstance()时才会执行，效率最高。
 * 加载一个类时，其内部类并不会同时加载，当一个类被加载，当且仅当遇到其某个静态成员（静态域，构造器，静态方法,反射等）被调用时发生。
 */
public class InnerClassSingleton {
    private static class SingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton(){
        System.out.println("initialize the singleton.");
    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        InnerClassSingleton instance=null;
        System.out.println("start testing...");
        // 当调用内部类的静态方法时，才会加载内部类
        instance= InnerClassSingleton.getInstance();
    }

}
