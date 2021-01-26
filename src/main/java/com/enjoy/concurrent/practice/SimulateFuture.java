package com.enjoy.concurrent.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
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


            long begin = System.currentTimeMillis();
            long timePassed = 0;
            while (result == null) {

                long waitTime = timeout - timePassed;
                if (waitTime <= 0) {
                    logger.info("超时了不等了。");
                    break;
                }
                logger.info("运算结果还未执行完成，等待" + waitTime + "结果中...");
                synchronized (lock) {
                    try {
                        lock.wait(waitTime);// milliseconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timePassed = System.currentTimeMillis() - begin;
                // 计算已等待多长时间。
                logger.info("等待" + timePassed + "毫秒");
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
            logger.info("【" + Thread.currentThread().getId() + "】正在计算");
            TimeUnit.SECONDS.sleep(5);
            logger.info("【" + Thread.currentThread().getId() + "】计算完成");
            return 999;
        });
        // start to execute the task.
        new Thread(futureTask).start();
        // blocking to get the result.
//        Integer result = futureTask.getResult();
        Integer result = futureTask.tryGetResult(6000);
        System.out.println(result);

    }





    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        int count=0;
        Arrays.sort(prices);
        int left=k;
        for (int i = 0; i <prices.length ; i++) {
            left=left-prices[i];
            if (left>=0)
                count++;
            else
                break;
        }
        return count;
    }

}
