package com.enjoy.concurrent.tools;

import com.enjoy.SleepUtil;

import java.util.concurrent.*;

/**
 * state状态值是一个volatile修饰，主线程new Thread(FutureTask).start()启动执行run()方法后，主线程执行FutureTask的get()方法获取执行结果。
 * 首先get()方法中判断state的状态，如果没有完成一直阻塞线程，直到state为completing状态后获取outCome执行结果，
 */
public class FutureTaskUsage {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> callable = new Accountant();
        // 封装Callable
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        // FutureTask->RunnerFuture接口->Future和Runnable接口
        // 在run()方法中调用Callable接口的call()方法，结果
        new Thread(futureTask).start();
        try {
            boolean needInterrupted = true;
            if (!needInterrupted) {
                // outCome变量获取执行结果
                Integer integer = futureTask.get();
                System.out.println("主线程获得结果为： " + integer);
            } else {
                SleepUtil.sleep(200);
                // 中断执行任务
                boolean cancelled = futureTask.cancel(true);
                System.out.println("中断执行任务结果: " + cancelled);
                // 如果被中断，无法通过get()方法获取执行结果。
               // System.out.println("主线程获得结果为： " + futureTask.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class Accountant implements Callable<Integer> {

    private int sum = 0;

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " start calculating.");

        for (int i = 0; i < 5000; i++) {
            // 如果中断
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " 任务已被中断，中断结果为：" + sum);
                return sum;
            }
            sum++;
            try {
                Thread.sleep(10);
            } catch (Exception e) {

                System.out.println(Thread.currentThread().getName() + " 休眠时打断不理会，中断结果为：" + sum);
                //重置中断标识
                Thread.currentThread().interrupt();
                //return sum;
            }
        }
        System.out.println(Thread.currentThread().getName() + " 已执行结束,结果为：" + sum);
        return sum;
    }
}


