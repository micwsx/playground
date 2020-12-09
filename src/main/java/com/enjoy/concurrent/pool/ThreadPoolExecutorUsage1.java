package com.enjoy.concurrent.pool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 所有的线程池主要是通过ThreadPoolExecutor构造的。如何设置线程池？
 * <p>
 * ThreadPoolExecutor定义参数
 * corePoolSize – 线程池中工作线程数
 * maximumPoolSize – 线程池中最大工作线程数
 * keepAliveTime – 线程数大于核心线程池数, 等待新任务的过剩的空闲线程最大等待时间
 * unit – keepAliveTime 时间单位
 * workQueue – 阻塞队列保存执行任务
 * threadFactory – 创建线程对象工厂
 * handler – 队列满后执行Handler
 */
public class ThreadPoolExecutorUsage1 {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorUsage1.class);

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                2,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        threadPoolExecutor.execute(() -> {
            logger.info("Task1");
        });
        threadPoolExecutor.execute(() -> {
            logger.info("Task2");
        });
        threadPoolExecutor.execute(() -> {
            logger.info("Task3");
        });

        threadPoolExecutor.shutdown();//队列中的任务还会执行
        //threadPoolExecutor.shutdownNow();//队列中的任务会摒弃
        // 关闭后，线程池拒绝接收任务
        threadPoolExecutor.execute(() -> {
            logger.info("Task4");
        });


    }
}
