package com.enjoy.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wu
 * @Date 4/7/2021 1:09 PM
 * @Version 1.0
 */
public class Publisher {

    private final List<Observer> list = new ArrayList<>();

    public void subscribe(Observer observer) {
        list.add(observer);
    }

    public void publish(String name) {
        for (Observer observer : list) {
            observer.action(this, new NewEvent(name));
        }
    }

    public static void main(String[] args) {
        Observer<NewEvent> observer = new Observer<>();
        Publisher publisher = new Publisher();
        publisher.subscribe(observer);
        publisher.publish("Good News");
    }

}
