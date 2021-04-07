package com.enjoy.redis;

import java.sql.SQLOutput;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author wu
 * @Date 4/1/2021 1:20 PM
 * @Version 1.0
 */
public class TestDistributedLock {

    private DistributedLock distributedLock = new DistributedLock();
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private int num = 0;

    private void increment() {
        distributedLock.lock();
        try {
            num++;
        } finally {
            distributedLock.unlock();
        }
    }

    private void decrease() {
        distributedLock.lock();
        try {
            num--;
        } finally {
            distributedLock.unlock();
        }
    }

    private void test() {


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                increment();
            }, "increment【" + i + "】");
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                decrease();
            }, "decrease【" + i + "】");
        }

        System.out.println("5s后开始....");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        TestDistributedLock testDistributedLock = new TestDistributedLock();
        testDistributedLock.test();
    }

}
