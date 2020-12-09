package com.enjoy.concurrent.customLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael
 * @create 12/8/2020 10:46 AM
 * 模拟Future实现，若计算结果没有出来阻塞，并运动阻塞超时机制
 */
public class SimulateFuture {

    private static Logger logger = LoggerFactory.getLogger(SimulateFuture.class);

    public static class FutureTask<T> implements Runnable {

        private Callable<T> target;

        private Object lock = new Object();

        public FutureTask(Callable<T> target) {
            this.target = target;
        }

        private T result;

        @Override
        public void run() {
            if (this.target != null) {
                try {
                    result = target.call();
                    logger.info("【" + Thread.currentThread().getId() + "】运算结果执行完成，通知其它线程拿结果。");
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * @param timeout 毫秒
         * @return
         */
        public T tryGetResult(long timeout) {
            long base = System.currentTimeMillis();
            long timePassed = 0;
            while (result == null) {
                try {
                    long waitTime = timeout - timePassed;
                    if (waitTime <= 0) {
                        logger.info(Thread.currentThread().getName() + "等待超时，不等了");
                        break;
                    }
                    logger.info(Thread.currentThread().getName() + "运算结果还未执行完成，等待" + waitTime + "结果中...");
                    synchronized (lock) {
                        lock.wait(waitTime);// milliseconds
                    }
                    // 计算已等待多长时间。
                    logger.info(Thread.currentThread().getName() + "等待" + waitTime + "毫秒");
                    timePassed = System.currentTimeMillis() - base;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }


        public T getResult() {
            synchronized (lock) {
                while (result == null) {
                    try {
                        logger.info("【" + Thread.currentThread().getId() + "】运算结果还未执行完成，等待结果中...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return result;
            }
        }

    }


    public static void main(String[] args) {

        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            logger.info("【" + Thread.currentThread().getName() + "】正在计算");
            TimeUnit.SECONDS.sleep(5);
            logger.info("【" + Thread.currentThread().getName() + "】计算完成");
            return 999;
        });
        // start to execute the task.
        new Thread(futureTask, "myThread").start();
        // blocking to get the result.
//        Integer result = futureTask.getResult();
        Integer result = futureTask.tryGetResult(2000);
        System.out.println(result);

    }
}
