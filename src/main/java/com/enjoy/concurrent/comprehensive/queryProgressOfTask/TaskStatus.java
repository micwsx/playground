package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

/**
 * 任务状态
 */
public enum TaskStatus {

    SUCCESS(0), FAILED(-1), EXCEPTION(-2);

    private final int code;

    TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
