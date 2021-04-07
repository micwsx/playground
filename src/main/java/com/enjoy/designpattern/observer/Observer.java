package com.enjoy.designpattern.observer;

/**
 * @Author wu
 * @Date 4/7/2021 1:10 PM
 * @Version 1.0
 * subscriber to subscribe the alteration from Publisher.
 */
public class Observer<T> {


    public void action(Object source, T event) {
        System.out.println(Thread.currentThread().getName() + ": source>>" + source + " event: " + event);
    }
}
