package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;
import sun.misc.Unsafe;

import java.util.concurrent.locks.LockSupport;

/**
 *
 */
public class LockSupportUsage {

    private static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" is blocking...");
            // 线程阻塞
            LockSupport.park();

            System.out.println(Thread.currentThread().getName()+" is running...");

        }
    }

    public static void main(String[] args) {

        Thread thread=new Thread(new Task());
        thread.start();
        SleepUtil.sleep(2000);
        // 线程唤醒
        LockSupport.unpark(thread);

    }
}
