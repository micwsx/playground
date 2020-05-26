package com.enjoy.concurrent.tools;

import com.enjoy.SleepUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 线程数不一定等于count
 * CountDownLatch实现线程模拟并发(发令枪)和线程同步
 */
public class CountDownLatchUsage {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" done");
                countDownLatch.countDown();
            },"Michael["+i+"]").start();
        }

        //trigger();
        System.out.println("等待线程执行结果。。。");
        // 主线程阻塞，直到所有
        countDownLatch.await();
        System.out.println("Main end");

    }



    private static void trigger() {
        CountDownLatch countDownLatch =new CountDownLatch(6);
        // 4个线程 countDown()4次
        for (int i = 1; i <=4 ; i++) {
            final int sleepTime=i*100;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is ready!");
                SleepUtil.sleep(sleepTime);
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is running");
            },"Michael["+i+"]").start();
            countDownLatch.countDown();
        }
        // 第5个线程
        new Thread(()->{
             System.out.println(Thread.currentThread().getName()+" is ready!");
             try {
                 countDownLatch.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(Thread.currentThread().getName()+" is running");
         },"Exclusive[5]").start();
        //主线程countDown()2次
        countDownLatch.countDown();
        countDownLatch.countDown();
    }
}


