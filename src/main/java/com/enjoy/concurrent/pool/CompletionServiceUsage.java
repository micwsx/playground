package com.enjoy.concurrent.pool;

import com.enjoy.SleepUtil;
import com.sun.org.apache.xpath.internal.functions.FuncTrue;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CompletionService；拿结果的时间，谁完成谁就先输出！
 */
public class CompletionServiceUsage {

    private static final int TASK_NUM = 100;

    public static void main(String[] args) {

        Task[] tasks = new Task[TASK_NUM];
        for (int i = tasks.length - 1; i >= 0; i--) {
            tasks[i] = new Task(i * new Random().nextInt(5));
        }

        testQueue(tasks);

        testCompletionService(tasks);
    }

    /**
     * 结果输出和线程的放入顺序有关(如果前面没有完成，就算后面完成了也要等前面完成才输出)，存在阻塞耗时。
     * @param tasks
     */
    public static void testQueue(Task[] tasks) {
        long startTime = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        // 拿任务执行结果
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>(TASK_NUM);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 提交任务
        for (int i = 0; i < tasks.length; i++) {
            Future<Integer> future = executorService.submit(tasks[i]);
            try {
                queue.put(future);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 开始获取结果
        for (int i = 0; i < tasks.length; i++) {
            try {
                Future<Integer> future = queue.take();
                count.addAndGet(future.get());
                System.out.println("index: " + i + "-value: " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("Sleep Time: " + count.get() + "ms, Total time:" + (endTime - startTime) + "ms");
    }


    /**
     * 结果的输出和线程放入顺序无关(谁完成谁就先输出！主线程总是拿到最先完成的任务的返回值，而不管它们加入线程池的顺序)，大大缩短等待时间。
     * @param tasks
     */
    public static void testCompletionService(Task[] tasks) {
        long startTime = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>(TASK_NUM);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService, queue);
        // 提交任务
        for (int i = 0; i < tasks.length; i++) {
            Future<Integer> future = completionService.submit(tasks[i]);
        }
        // 开始拿结果
        for (int i = 0; i < tasks.length; i++) {
            try {
                Future<Integer> future = completionService.take();
                count.addAndGet(future.get());
                System.out.println("CIndex: " + i + "-value: " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("Sleep Time: " + count.get() + "ms, Total time:" + (endTime - startTime) + "ms");
    }


    private static class Task implements Callable<Integer> {

        private int num;

        public Task(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        @Override
        public Integer call() throws Exception {
            //int val = new Random().nextInt(1000);
            // SleepUtil.sleep(val);
            SleepUtil.sleep(num);
            return num;
        }
    }
}
