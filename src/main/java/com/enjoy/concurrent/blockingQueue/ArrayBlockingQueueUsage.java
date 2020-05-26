package com.enjoy.concurrent.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列实现机制是等待/通知机制。通过Lock显示锁的Condition的signal()和await()方法实现。保证线程同步安全访问
 *
 * ArrayBlockingQueue:一个数组结构的有界队列
 * BlockQueue接口，支持非阻塞式add/remove,offer/poll,(put/take阻塞方式)
 * 阻塞的元素变动方法，插入和移除操作。插入满时阻塞，移除空时阻塞。
 * 默认FIFO，也可以设置非FIFO
 */
public class ArrayBlockingQueueUsage {

    public static void main(String[] args) {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
//        arrayBlockingQueue.put();
//        arrayBlockingQueue.offer();


    }

}
