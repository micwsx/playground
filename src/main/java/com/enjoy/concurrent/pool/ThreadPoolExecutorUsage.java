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

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1), new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "WorkerThread[" + count.incrementAndGet() + "]");
                return thread;
            }
        }, new RejectHandler()) {

            private void printInfo(){
                int corePoolSize = this.getCorePoolSize();
                int maximumPoolSize = this.getMaximumPoolSize();
                int poolSize = this.getPoolSize();
                int activeCount = this.getActiveCount();
                int size = this.getQueue().size();
                long completedTaskCount = this.getCompletedTaskCount();
                long taskCount = this.getTaskCount();
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("corePoolSize: "+corePoolSize).append(System.lineSeparator());
                stringBuilder.append("maximumPoolSize: "+maximumPoolSize).append(System.lineSeparator());
                stringBuilder.append("poolSize: "+poolSize).append(System.lineSeparator());
                stringBuilder.append("activeCount: "+activeCount).append(System.lineSeparator());
                stringBuilder.append("size: "+size).append(System.lineSeparator());
                stringBuilder.append("completedTaskCount: "+completedTaskCount).append(System.lineSeparator());
                stringBuilder.append("taskCount: "+taskCount).append(System.lineSeparator());
                System.out.println(stringBuilder.toString());
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println(Thread.currentThread().getName()+"-----------beforeExecute()-----------");
                printInfo();
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                System.out.println(Thread.currentThread().getName()+"-----------afterExecute()-----------");
                printInfo();
            }

            @Override
            protected void terminated() {
//                super.terminated();
//                System.out.println("-----------terminated()-----------");
            }
        };

        for (int i = 1; i <= 3; i++) {
            final int num = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+ " 打印 " + num);
                }
            });
        }

        System.out.println(Thread.currentThread().getName()+" is finished");

//       threadPoolExecutor.shutdown();
    }

    private static class RejectHandler extends ThreadPoolExecutor.CallerRunsPolicy{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println(Thread.currentThread().getName()+" trigger rejectedExecution. wait 30s");
//            try {
//                TimeUnit.SECONDS.sleep(30);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
            super.rejectedExecution(r, e);
        }

    }
}
