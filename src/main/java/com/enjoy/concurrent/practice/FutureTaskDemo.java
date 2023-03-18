package com.enjoy.concurrent.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: Michael
 * @date: 2023/3/4 10:13
 * 线程池中的线程被中断后，还是能被re-use，不影响后面任务提交执行。
 */
public class FutureTaskDemo {

    private static final Logger logger= LoggerFactory.getLogger(FutureTaskDemo.class);

    private static class MyThreadPool extends ThreadPoolExecutor{

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (t!=null){
                logger.error("afterExecute",r,t);
            }else {
                logger.info("{},{}",r,t);
            }
        }
    }




    private static ExecutorService executorService = new MyThreadPool(1, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

//    private static ExecutorService executorService = new ThreadPoolExecutor(1, 2,
//            0L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) FutureTaskDemo.executorService;

        // poolSize：0 corePoolSize：2 activeCount：0 taskCount：0
        printPoolInfo(executorService);

        Future<String> future = FutureTaskDemo.executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
//                TimeUnit.SECONDS.sleep(5);
//                while (!Thread.currentThread().isInterrupted()) {
//                    while (!Thread.interrupted()) {
                    while (true&&!Thread.interrupted()) {
    //                    throw new Exception("my error");
                       logger.info("calculating...");
                    }
                    logger.info("dddddddddddd dddddd");
                } finally {
                    logger.info("task finally");
                }
                return "task finished.";
            }
        });

        logger.info("-----------");
        // poolSize：1 corePoolSize：2 activeCount：1 taskCount：1
        printPoolInfo(executorService);


        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean cancel = future.cancel(true);
        logger.info("{}",cancel);

        try {
            String result  = future.get();
            logger.info(result);
        } catch (CancellationException e) {
            logger.error("CancellationException",e);
        } catch (InterruptedException e) {
            logger.error("InterruptedException",e);
        } catch (ExecutionException e) {
            logger.error("ExecutionException",e);
        }
//        printPoolInfo(executorService);
//
//
//        logger.info("------execute 2 task.------");
//        List<Future<String>> list=new ArrayList<>();
//
//        list.add(FutureTaskDemo.executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                TimeUnit.SECONDS.sleep(5);
//                return "task1 finished.";
//            }
//        }));
//
//        list.add(FutureTaskDemo.executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                TimeUnit.SECONDS.sleep(5);
//                return "task2 finished.";
//            }
//        }));
//
//        printPoolInfo(executorService);
//        for (Future<String> stringFuture : list) {
//            try {
//                logger.info(stringFuture.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        logger.info("------------");
//        printPoolInfo(executorService);
    }

    private static void printPoolInfo(ThreadPoolExecutor executorService) {
        int poolSize = executorService.getPoolSize();
        int corePoolSize = executorService.getCorePoolSize();
        int activeCount = executorService.getActiveCount();
        long taskCount = executorService.getTaskCount();
        long completedTaskCount = executorService.getCompletedTaskCount();
        int maximumPoolSize = executorService.getMaximumPoolSize();
        int largestPoolSize = executorService.getLargestPoolSize();
        int queueSize = executorService.getQueue().size();

        StringBuilder poolInfo = new StringBuilder();

        poolInfo.append("largestPoolSize:" + largestPoolSize).append(System.lineSeparator());
        poolInfo.append("maximumPoolSize:" + maximumPoolSize).append(System.lineSeparator());
        poolInfo.append("poolSize:" + poolSize).append(System.lineSeparator());
        poolInfo.append("corePoolSize:" + corePoolSize).append(System.lineSeparator());
        poolInfo.append("activeCount:" + activeCount).append(System.lineSeparator());
        poolInfo.append("taskCount:" + taskCount).append(System.lineSeparator());
        poolInfo.append("completedTaskCount:" + completedTaskCount).append(System.lineSeparator());
        poolInfo.append("queueSize:" + queueSize).append(System.lineSeparator());
        logger.info(poolInfo.toString());
    }
}
