package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

/**
 * 任务结果对象
 */
public class TaskResult<R> {

    private TaskStatus taskStatus;
    private String message;
    private R result;

    public TaskResult(TaskStatus taskStatus, R result, String message) {
        this.taskStatus = taskStatus;
        this.message = message;
        this.result = result;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getMessage() {
        return message;
    }

    public R getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "taskStatus=" + taskStatus +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
