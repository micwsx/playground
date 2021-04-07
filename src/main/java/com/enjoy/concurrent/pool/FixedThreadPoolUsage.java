package com.enjoy.concurrent.pool;

import com.enjoy.SleepUtil;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 使用线程池原因：
 * 1。减少线程创建从而减少资源消耗
 * 2。提高响应速度（创建->运行->消毁。直接从线程池中拿线程运行任务）
 * 3。提高线程的可管理性
 * FixedThreadPool: 使用默认DefaultThreadFactory和默认AbortPolicy
 * new new ThreadPoolExecutor(
 * corePoolSize: nThreads
 * maximumPoolSize: nThreads
 * keepAliveTime:0
 * TimeUnit: TimeUnit.MILLISECONDS
 * BlockingQueue<Runnable>: LinkedBlockingQueue<Runnable>
 * ThreadFactory: DefaultThreadFactory
 * RejectedExecutionHandler: AbortPolicy)
 */
public class FixedThreadPoolUsage {

    public static void main(String[] args) {

        ExecutorService executorService1 = Executors.newFixedThreadPool(1);



        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newCachedThreadPool();
        // 守线工作线程（主程序结束后，工作线程直接结束）
//        executorService = Executors.newWorkStealingPool();

//        executorService=Executors.newFixedThreadPool(10, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread thread=new Thread(r);
//                thread.setName("Task["+Thread.currentThread().getId()+"]");
//                return thread;
//            }
//        });

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " 准备返回值！");
                return "Michael";
            }
        });

        for (int i = 1; i <= 100; i++) {
            final int num = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(5) * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 任务执行结束！" + num);
                }
            });
        }

        try {
            String name = future.get();
            System.out.println("Future result: " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //SleepUtil.sleep(2000);
        // shutdown不会立刻停止工作线程执行任务，只是通知不能再添加任务，会等待正在执行的线程执行结束，未执行的任务不会执行。
        executorService.shutdown();
        // shutdownNow立刻停止工作线程执行任务，所有工程线程都停止执行任务（包括正在执行的线程）
        // executorService.shutdownNow();
    }
}
