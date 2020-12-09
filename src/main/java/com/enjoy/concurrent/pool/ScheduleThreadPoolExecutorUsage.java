package com.enjoy.concurrent.pool;


import com.enjoy.SleepUtil;

import java.util.concurrent.*;

/**
 * Schedule定时执行任务，为了防止异常，建议在业务代码中添加异常捕获，否则Schelue执行过程中不会获取任务异常消息。
 */
public class ScheduleThreadPoolExecutorUsage {

    public static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            //SleepUtil.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " 准备返回值！");
            return "Michael";
        }
    }

    public static class TaskRun implements Runnable {
        @Override
        public void run() {
            try {
                //SleepUtil.sleep(3000);
                int i=1/0;
                System.out.println(Thread.currentThread().getName() + " 任务执行结束！");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        TaskRun taskRun=new TaskRun();
//        Task task=new Task();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.execute(taskRun);// 如果代码中有异常，会吞掉异常。所有代码加入异常处理代码。

//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(taskRun);// 抛异常

//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        singleThreadExecutor.execute(taskRun);

        // 任务只会执行一次
//        scheduledExecutorService.schedule(taskRun, 3, TimeUnit.SECONDS);
//        scheduledExecutorService.schedule(task, 3,TimeUnit.SECONDS);
        // 多次执行，2个任务执行相隔时间3s
        //scheduledExecutorService.scheduleAtFixedRate(taskRun, 2, 3, TimeUnit.SECONDS);
        //多次执行，一个任务结束后与下一个任务开始相隔时间3s，如果上一个任务开始后执行超过3秒，则下一个任务等待前一个任务执行结束后立刻执行。
        //scheduledExecutorService.scheduleWithFixedDelay(taskRun, 2, 3, TimeUnit.SECONDS);

//        ScheduledFuture scheduledFuture=scheduledExecutorService.schedule(task, 3,TimeUnit.SECONDS);
//        ScheduledFuture scheduledFuture=scheduledExecutorService.scheduleWithFixedDelay(taskRun, 2, 3, TimeUnit.SECONDS);

    }
}
