package com.enjoy.concurrent.customThreadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Handler;

/**
 * @author Michael
 * @create 12/7/2020 3:44 PM
 * 自定义线程池
 */
public class CustomThreadPool {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // 核心线程
    private int core;

    //工作线程集合
    private HashSet<MyWorkThread> workThreads;

    //任务集合
    private TaskQueue taskQueue;

    public TaskQueue getTaskQueue() {
        return taskQueue;
    }

    /***
     *
     * @param core 工作线程数量
     * @param limitCapacity 存放任务空间大小
     */
    public CustomThreadPool(int core, int limitCapacity, PolicyHandler handler) {
        this.core = core;
        workThreads = new HashSet<>();
        taskQueue = new TaskQueue(limitCapacity, this, handler);
    }

    // 提交任务
    public void submit(MyTask task) {
        //如果提交任务数量大于核心线程数，则添加到任务队列中。
        if (workThreads.size() >= core) {
            logger.info("核心线程已用完，任务" + task.getName() + "被添加到任务队列中等待处理。");
            taskQueue.put(task);
        } else {
            logger.info("核心线程，任务" + task.getName() + "被添加到任务队列中等待处理。");
            // 没有超过工作线程则直接运行。
            MyWorkThread thread = new MyWorkThread(task, "MyWorkThread[" + workThreads.size() + "]", this);
            workThreads.add(thread);
            thread.start();
        }
    }


    public static void main(String[] args) {

        PolicyHandler discardOldestPolicy = (threadPool, task) -> {
            threadPool.getTaskQueue().poll();
            threadPool.submit(task);
        };

        PolicyHandler discardPolicy = (threadPool, task) -> {
            //do nothing
        };

        PolicyHandler abortPolicy = (threadPool, task) -> {
            throw new RejectedExecutionException("Task " + task.getName() +
                    " rejected from " +
                    threadPool);
        };

        PolicyHandler callerRunsPolicy = (threadPool, task) -> {
            task.run();
        };


        CustomThreadPool pool = new CustomThreadPool(2, 1, abortPolicy);
        for (int i = 1; i <= 4; i++) {
            pool.submit(new MyTask("task" + i));
        }
    }

}
