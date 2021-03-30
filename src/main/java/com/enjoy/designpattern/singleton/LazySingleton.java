package com.enjoy.designpattern.singleton;

/**
 * @Author wu
 * @Date 3/25/2021 12:16 PM
 * @Version 1.0
 *
 */
public class LazySingleton {

    private static LazySingleton instance;

    public static synchronized LazySingleton getInstance(){
        if (instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }


    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();

    }
}
