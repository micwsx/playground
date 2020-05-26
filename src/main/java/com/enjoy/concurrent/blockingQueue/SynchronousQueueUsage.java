package com.enjoy.concurrent.blockingQueue;

import com.enjoy.SleepUtil;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue:一个不存储元素的阻塞队列。
 * BlockQueue接口，支持非阻塞式add/remove,offer/poll,(put/take阻塞方式)
 * 阻塞的元素变动方法，插入和移除操作。插入满时阻塞，移除空时阻塞。
 */
public class SynchronousQueueUsage {

    public static void main(String[] args) {

        SynchronousQueue synchronousQueue = new SynchronousQueue();

        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    SleepUtil.sleep(1000);
                    synchronousQueue.put(++i);
                    System.out.println(Thread.currentThread().getName() + " put " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Michael").start();

        new Thread(() -> {
            while (true) {
                try {
                    SleepUtil.sleep(1000);
                    int value = (int) synchronousQueue.take();
                    System.out.println(Thread.currentThread().getName() + " take " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Leo").start();



    }
}
