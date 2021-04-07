package com.enjoy.concurrent.pool;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 所有的线程池主要是通过ThreadPoolExecutor构造的。如何设置线程池？
 *
 * ThreadPoolExecutor定义参数
 * corePoolSize – 线程池中工作线程数
 * maximumPoolSize – 线程池中最大工作线程数
 * keepAliveTime – 线程数大于核心线程池数, 等待新任务的过剩的空闲线程最大等待时间
 * unit – keepAliveTime 时间单位
 * workQueue – 阻塞队列保存执行任务
 * threadFactory – 创建线程对象工厂
 * handler – 队列满后执行Handler
 */
public class ThreadPoolExecutorUsage {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                2,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2), new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "WorkerThread[" + count.incrementAndGet() + "]");
                return thread;
            }
        }, new ThreadPoolExecutor.DiscardOldestPolicy()) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println("-----------beforeExecute()-----------");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                System.out.println("-----------afterExecute()-----------");
            }

            @Override
            protected void terminated() {
                super.terminated();
                System.out.println("-----------terminated()-----------");

            }
        };

        for (int i = 1; i <= 100; i++) {
            final int num = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+ " 打印 " + num);
                }
            });
        }

       threadPoolExecutor.shutdown();


    }
}
