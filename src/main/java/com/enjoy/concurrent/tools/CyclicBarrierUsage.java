package com.enjoy.concurrent.tools;

import com.enjoy.SleepUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 线程数必须等于parties
 * CyclicBarrier的await()方法可以执行多次，循环再执行。
 *
 */
public class CyclicBarrierUsage {

    public static void main(String[] args) {

        SummaryTaskResult();

        // NoReturnTask();
    }

    private static void SummaryTaskResult() {
        ConcurrentHashMap<Thread, Integer> resultHashMap = new ConcurrentHashMap<Thread, Integer>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Estimation(resultHashMap));

        for (int i = 1; i <= 5; i++) {
            final int sleepTime = i * 100;
            new Thread(() -> {
                SleepUtil.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + " 跑完1公里！");
                resultHashMap.put(Thread.currentThread(), sleepTime);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "Michael[" + i + "]").start();
        }
    }

    private static void NoReturnTask() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            final int sleepTime = i * 1000;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开跑!");
                try {
                    SleepUtil.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName() + " 已跑1公里！");
                    cyclicBarrier.await();
                    SleepUtil.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName() + " 继续跑1公里！");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " 已跑完2公里！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                }
            }, "Michael[" + i + "]").start();
        }
    }

}

/**
 * 汇总所有线程时间
 */
class Estimation implements Runnable {

    private ConcurrentHashMap<Thread, Integer> resultHashMap;

    public Estimation(ConcurrentHashMap<Thread, Integer> resultHashMap) {
        this.resultHashMap = resultHashMap;
    }

    @Override
    public void run() {
        int totalTime = 0;
        for (Thread thread : resultHashMap.keySet()) {
            Integer result = resultHashMap.get(thread);
            totalTime = totalTime + result;
            System.out.println(thread.getName() + " Elapsed Time: " + result);
        }
        System.out.println("Total Time: " + totalTime);
    }
}


