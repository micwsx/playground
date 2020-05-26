package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;
import sun.misc.Unsafe;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 通过继承AbstractQueuedSynchronizer类CAS设置锁状态，LockSupport阻塞唤醒线程
 * AQS使用链接结构保存线程之间的关系。
 * 以下自定义一个BlockingQueue阻塞队列，存放等待获取锁的线程，当线程释放锁后则从阻塞队列中唤醒等待线程。
 */
public class CustomCASLock {

    private static class LockSyn extends AbstractQueuedSynchronizer {

        private LinkedBlockingQueue<Thread> parkQueue = new LinkedBlockingQueue<>();

        public void lock() {
            // 自旋设置锁的状态
            while (!compareAndSetState(0, 1)) {
                park();
            }
        }

        // 没有抢到锁的线程则park
        private void park() {
            parkQueue.add(Thread.currentThread());
            // park 释放CPU资源
            LockSupport.park(Thread.currentThread());
        }

        public void unLock() {
            // 修改锁状态
            compareAndSetState(1, 0);
            if (parkQueue.size()>0)
            {
                // 唤醒阻塞线程
                Thread t = parkQueue.remove();
                LockSupport.unpark(t);
            }
        }
    }

    private LockSyn lockSyn=new LockSyn();

    public void lock(){
        lockSyn.lock();
    }

    public void unLock(){
        lockSyn.unLock();
    }

    private static class TestLock implements Runnable {

        private CustomCASLock lock = new CustomCASLock();
        private int count = 0;

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁。");
                SleepUtil.sleep(new Random().nextInt(5) * 10);
                count = count + 1;
                System.out.println(count);
            } finally {
                System.out.println(Thread.currentThread().getName()+"释放锁。");
                lock.unLock();
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {

        TestLock testLock = new TestLock();
        for (int i = 0; i < 10; i++) {
            new Thread(testLock).start();
        }

//
//        CompletableFuture.supplyAsync(() -> {
//            TestLock testLock = new TestLock();
//            for (int i = 0; i < 1000; i++) {
//                new Thread(testLock).start();
//            }
//            return testLock;
//        }).thenAccept(arg -> {
//            int result = arg.getCount();
//            System.out.println("结果为：" + result);
//        });


    }
}
