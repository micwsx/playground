package com.enjoy.concurrent.blockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue：一个优先级无界阻塞队列
 * BlockQueue接口，支持非阻塞式add/remove,offer/poll,(put/take阻塞方式)
 * 阻塞的元素变动方法，插入和移除操作。插入满时阻塞，移除空时阻塞。
 * 值为能为null并且实现Comparable接口
 */
public class PriorityBlockingQueueUsage {

    public static void main(String[] args) {
        PriorityBlockingQueue priorityBlockingQueue=new PriorityBlockingQueue();
        priorityBlockingQueue.put(1);
        priorityBlockingQueue.put(2);
        priorityBlockingQueue.put(10);

        try {
            System.out.println(priorityBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        priorityBlockingQueue.add()
    }
}