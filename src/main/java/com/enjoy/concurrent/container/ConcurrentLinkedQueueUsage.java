package com.enjoy.concurrent.container;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkQueue是一个链接数据结构的Queue
 */
public class ConcurrentLinkedQueueUsage {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue();

        concurrentLinkedQueue.offer("English");

        System.out.println(concurrentLinkedQueue.poll());
    }
}
