package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class JobInfo {

    // 任务总数量
    private final int size;
    private final long expiryTime;
    // 任务名称
    private final String jobName;
    // 任务执行器
    private final ITask<Integer, TaskResult> taskProcessor = new TaskProcessor(this);

    // 任务结果集合
    private final LinkedList<Integer> taskQueue = new LinkedList();

    // 已处理任务数
    private int processNum = 0;

    // 任务结果集合
    private LinkedList<TaskResult> successNum = new LinkedList();

    private LinkedList<TaskResult> failureNum = new LinkedList();

    private LinkedList<TaskResult> exceptionNum = new LinkedList();


    /**
     * @param size：总数量
     * @param expiryTime 单位：秒
     * @param jobName：名称
     */
    public JobInfo(int size, int expiryTime, String jobName) {
        this.size = size;
        this.expiryTime = expiryTime * 1000 + System.currentTimeMillis();
        this.jobName = jobName;

    }


    // 属性方法
    public String getJobName() {
        return jobName;
    }

    public int getSize() {
        return size;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public ITask<Integer, TaskResult> getTaskProcessor() {
        return taskProcessor;
    }

    public void incrementProcessNum() {
        processNum++;
    }

    public int getProcessNum() {
        return processNum;
    }

    public LinkedList<TaskResult> getExceptionNum() {
        return exceptionNum;
    }

    public LinkedList<TaskResult> getSuccessNum() {
        return successNum;
    }

    public LinkedList<TaskResult> getFailureNum() {
        return failureNum;
    }

    private String showDetail() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("-------SUCCESS------\r\n");
        for (TaskResult taskResult : successNum) {
            stringBuffer.append(taskResult).append("\r\n");;
        }
        stringBuffer.append("-------FAILURE------\r\n");
        for (TaskResult taskResult : failureNum) {
            stringBuffer.append(taskResult).append("\r\n");
        }
        stringBuffer.append("-------EXCEPTION------\r\n");
        for (TaskResult taskResult : exceptionNum) {
            stringBuffer.append(taskResult).append("\r\n");
        }
        return stringBuffer.toString();
    }

    public String showProgress() {
        String header = "Total:[" + processNum + "] - Success[" + successNum.size() + "]" +
                ", Failure[" + failureNum.size() + "]" +
                ", Exception[" + exceptionNum.size() + "]\r\n";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(header);
        stringBuffer.append(showDetail());
        return stringBuffer.toString();
    }

    public void addTask(Integer value) {
        synchronized (this.taskQueue) {
            taskQueue.add(value);
        }
    }

    public LinkedList<Integer> getTaskQueue() {
        synchronized (this.taskQueue) {
            return taskQueue;
        }
    }
}
