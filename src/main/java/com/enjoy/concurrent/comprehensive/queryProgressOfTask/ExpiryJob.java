package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ExpiryJob implements Delayed {

    private JobInfo jobInfo;
    public ExpiryJob(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    public JobInfo getJobInfo() {
        return jobInfo;
    }

    // interface implementation
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.jobInfo.getExpiryTime() - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long res = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (res > 0) {
            return 1;
        } else if (res < 0) {
            return -1;
        }
        return 0;
    }
}
