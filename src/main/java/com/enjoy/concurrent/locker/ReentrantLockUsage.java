package com.enjoy.concurrent.locker;

import com.enjoy.SleepUtil;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReetrantLock可重入锁，显示锁使用,默认使用非公平锁NonfairSync
 */
public class ReentrantLockUsage implements Runnable {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Result result;

    public ReentrantLockUsage(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            SleepUtil.sleep(new Random().nextInt(5) * 10);
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入代码区！");
            try {
                result.setResult(result.getResult() + 1);
            } finally {
                System.out.println(Thread.currentThread().getName() + " 退出代码区！" + result.getResult());
                reentrantLock.unlock();
            }
        }
        // 线程执行结束countDown()
        result.getCountDownLatch().countDown();
    }

    private static class Result {
        private CountDownLatch countDownLatch;
        private int result;

        public  Result(int coutnDown){
            this.countDownLatch=new CountDownLatch(coutnDown);
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }
    }

    public static void main(String[] args) {

        int threadNum=10;
        Result result = new Result(threadNum);
        ReentrantLockUsage task = new ReentrantLockUsage(result);

        FutureTask[] futureTask = new FutureTask[threadNum];
        // 10个线程执行一个任务
        for (int i = 0; i < threadNum; i++) {
            futureTask[i] = new FutureTask<Result>(task, result);
        }
        //启动线程
        for (int i = 0; i < futureTask.length; i++) {
            new Thread(futureTask[i], "Michael[" + i + "]").start();
        }

        try {
            result.getCountDownLatch().await();
            System.out.println("结果为:" + result.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//             // 不能使用此futureTask[0].get()方法获取结果，因为其它线程并没有执行结束此结果并不是最后结果。只能使用CountDownLatch并必工具await()阻塞，等待最后执行结果。
//            Result finalResult=(Result)futureTask[0].get();
//            System.out.println("结果为:" + finalResult.getResult());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }

}
