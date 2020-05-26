package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义一个线程池：
 * 基本参数：
 * 线程数量
 * 存放任务容器（队列）
 */
public class CustomThreadPool {

    // 存放任务队列阻塞队列
    private BlockingQueue<Runnable> blockingQueue;
    private AtomicInteger poolSize = new AtomicInteger(0);

    public CustomThreadPool(int poolSize, int capacity) {
        this.poolSize.set(poolSize);
        this.blockingQueue = new LinkedBlockingQueue<>(capacity);
    }

    public void execute(Runnable command) {
        if (poolSize.decrementAndGet() >= 0) {
            Worker worker = new Worker(command);
            worker.thread.start();
        } else {
            System.out.println("工作线程已达设置数量,任务已提交，所有线程已全部在执行你的任务请耐心等待。。。");
            try {
                blockingQueue.put(command);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void runWork(Worker worker) {
        Runnable w = worker.task;
        while ((w != null || (w = getTask()) != null)) {
            worker.lock();
            try {
                w.run();
            } finally {
                worker.completedTasks++;
                System.out.println(Thread.currentThread().getName() + "完成任务！" + worker.completedTasks);
                w = null;
                worker.unLock();
            }
        }
        System.out.println(Thread.currentThread().getName() +"没有任务执行！");
    }

    private Runnable getTask() {
        for (; ; ) {
            if (blockingQueue.size() <= 0)
                break;
            try {
                Runnable runnable = blockingQueue.take();
                return runnable;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private class Worker extends AbstractQueuedSynchronizer implements Runnable {

        private long completedTasks;
        private Runnable task;
        private Thread thread;

        public Worker(Runnable task) {
            this.task = task;
            this.thread = new Thread(this);
        }

        public void lock() {
            acquire(1);
        }

        public void unLock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }


        @Override
        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }


        @Override
        public void run() {
            runWork(this);
        }
    }


    public static void main(String[] args) {

        CustomThreadPool customThreadPool = new CustomThreadPool(2, 10);

        for (int i = 0; i < 10; i++) {
            final int num = i;

//            new Thread(() -> {
//                SleepUtil.sleep(new Random().nextInt(5) * 1000);
//                System.out.println("Body Number-" + num);
//            }).start();

            customThreadPool.execute(() -> {
                SleepUtil.sleep(new Random().nextInt(5) * 1000);
                System.out.println("Body Number-" + num);
            });
        }


    }
}
