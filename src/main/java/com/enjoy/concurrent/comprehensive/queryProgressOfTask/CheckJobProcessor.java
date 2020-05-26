package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

import java.util.*;
import java.util.concurrent.*;

public class CheckJobProcessor {

    // 过期job会自动出现些队列中
    private final DelayQueue<ExpiryJob> delayQueue = new DelayQueue<>();

    // 存放JobInfo
    private final Set<JobInfo> jobInfoSet = Collections.synchronizedSet(new HashSet());

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static class CheckJobProcessorHolder {
        private static CheckJobProcessor checkJobProcessor = new CheckJobProcessor();
    }

    public static CheckJobProcessor getInstance() {
        return CheckJobProcessorHolder.checkJobProcessor;
    }

    public void registerJobInfo(JobInfo jobInfo) {
        System.out.println("注册JobInfo名称: " + jobInfo.getJobName());
        if (jobInfoSet.contains(jobInfo))
            throw new IllegalArgumentException("JobInfo:" + jobInfo.getJobName() + " 已存在！");
        jobInfoSet.add(jobInfo);
    }

    public Set<JobInfo> getJobInfoSet() {
        return jobInfoSet;
    }

    public void executeJobs() {
        for (JobInfo jobInfo : jobInfoSet) {
            LinkedList<Integer> taskQueue = jobInfo.getTaskQueue();
            Integer task;
            while ((task = taskQueue.poll()) != null) {
                // 线程池执行提交任务
                Integer finalTask = task;
                executorService.submit(() -> {
                    try {
                        TaskResult taskResult = jobInfo.getTaskProcessor().calculate(finalTask);
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }
        System.out.println("所以任务都已提交完成，等待处理结果！");
    }

    public DelayQueue<ExpiryJob> getDelayQueue() {
        return delayQueue;
    }

    static {
        // 启动后端线程检查Job过期任务
        Thread thread = new Thread(() -> {
            CheckJobProcessor checkJobProcessor = CheckJobProcessor.getInstance();
            DelayQueue<ExpiryJob> queue = checkJobProcessor.delayQueue;
            while (true) {
                try {
                    Thread.sleep(10);
                    ExpiryJob expiryJob = queue.take();
                    // 停止此job任务
                    checkJobProcessor.getJobInfoSet().remove(expiryJob.getJobInfo());
                    System.out.println("JobInfo:" + expiryJob.getJobInfo().getJobName() + " 已过期，被移除！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DaemonOfDelayQueue");
        thread.setDaemon(true);
        thread.start();
        System.out.println("启动守护线程：" + thread.getName());
    }

    public static void main(String[] args) {
        int size = 200;
        CheckJobProcessor checkJobProcessor = CheckJobProcessor.getInstance();
        // Job执行结束后10s将超时处理。
        JobInfo jobInfo = new JobInfo(size, 10, "EvenOrOdd");
        //
        for (int i = 1; i <= size; i++) {
            Integer task = new Integer(i);
            jobInfo.addTask(task);
        }
        // 注册一个Job
        checkJobProcessor.registerJobInfo(jobInfo);

        checkJobProcessor.executeJobs();

        // 每隔3秒打倒执行进度
      ScheduledExecutorService executorService=  Executors.newSingleThreadScheduledExecutor();

      executorService.scheduleAtFixedRate(()->{
          for (JobInfo info : checkJobProcessor.getJobInfoSet()) {
              String progress=info.showProgress();
              System.out.println(progress);
          }
      }, 2, 3, TimeUnit.SECONDS);
    }
}
