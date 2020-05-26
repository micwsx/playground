package com.enjoy.concurrent.tools;

import com.enjoy.SleepUtil;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量,许可证数量，能限制同时执行代码块线程个数（限流作用）
 * acquire()获取许可数量,可以获取多个
 * release()归还许可数量,可以归还多个
 */
public class SemaphoreUsage {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            final int sleepTime = i * 100;
            new Thread(() -> {
                try {
                    semaphore.acquire(5);
                    System.out.println(Thread.currentThread().getName() + " 进来了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SleepUtil.sleep(sleepTime);
                    semaphore.release(5);
                }
            }, "Michael[" + i + "]").start();
        }
    }
}
