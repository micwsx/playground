package com.enjoy.concurrent.singleton;

/**
 * 饿汉模式
 */
public class SingletonEHan {

    private static SingletonEHan instance = new SingletonEHan();

    private int age = 18;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static SingletonEHan getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SingletonEHan instance = SingletonEHan.getInstance();
        instance.getAge();
    }
}

