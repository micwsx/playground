package com.enjoy.concurrent.foundation.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * -Xmx10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Workstation\jvm.dump
 */
public class ThreadLocalStorage4 implements Runnable {

    private static final ThreadLocal<LocalClass> threadLocal = new ThreadLocal<LocalClass>();

    private static class LocalClass {
        byte[] date = new byte[1 * 1024 * 1024];//1M
    }

    @Override
    public void run() {
        while (true) {
            // 单个线程内执行并不会造成内存泄漏，线程一直不停的操作map而已。
            threadLocal.set(new LocalClass());
            System.out.println(threadLocal.get());
            //        threadLocal.remove();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadLocalStorage4());
        thread.start();
    }

}
