package com.enjoy.designpattern.observer;

/**
 * @Author wu
 * @Date 4/7/2021 1:11 PM
 * @Version 1.0
 */
public class NewEvent {

    private String topic;

    public NewEvent(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "NewEvent{" +
                "topic='" + topic + '\'' +
                '}';
    }
}
