package com.enjoy.concurrent.customThreadPool;

import com.enjoy.concurrent.customLock.LockerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Michael
 * @create 12/7/2020 4:04 PM
 */
public class MyTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(LockerMain.class);
    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName() + "执行任务" + this.name);
    }
}
