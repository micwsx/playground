package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

import java.util.Random;

/**
 * 判断100内数值是否奇偶。
 */
public class TaskProcessor implements ITask<Integer, TaskResult> {

    private JobInfo jobInfo;

    public TaskProcessor(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    @Override
    public TaskResult calculate(Integer value) throws Throwable{
        TaskResult taskResult;
        Random random = new Random();
        int sleepTime = random.nextInt(5) * 10;
        Thread.sleep(sleepTime);
        // 求余结果
        int remainder = value & 1;

        synchronized (this) {
            // 判断是奇数还是偶数
            if (remainder == 0) {
                // 偶数处理正常
                taskResult = new TaskResult(TaskStatus.SUCCESS, remainder, "成功");
                this.jobInfo.getSuccessNum().add(taskResult);
            } else if (value <= 100) {
                // 奇数中小于100失败
                taskResult = new TaskResult(TaskStatus.FAILED, remainder, "(" + value + " & 1)=" + remainder + "，奇数<=100失败！");
                this.jobInfo.getFailureNum().add(taskResult);
            } else {
                // 奇数中大于100异常
                taskResult = new TaskResult(TaskStatus.EXCEPTION, remainder, "(" + value + " & 1)=" + remainder + "，奇数>100失败！");
                this.jobInfo.getExceptionNum().add(taskResult);
            }
            // 总数加一
            this.jobInfo.incrementProcessNum();
            // 得到所有结果后等待指定时间后将任务放入延时队列移除，结果将过期不被保存。
            if (this.jobInfo.getProcessNum() == this.jobInfo.getSize()) {
                CheckJobProcessor.getInstance().getDelayQueue().add(new ExpiryJob(this.jobInfo));
            }
        }
        return taskResult;
    }
}