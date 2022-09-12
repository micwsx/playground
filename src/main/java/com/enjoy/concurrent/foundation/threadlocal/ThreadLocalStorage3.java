package com.enjoy.concurrent.foundation.threadlocal;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * -Xmx1M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Workstation\jvm.dump
 */
public class ThreadLocalStorage3 implements Runnable {

    private static final ThreadLocal<LocalClass> threadLocal = new ThreadLocal<LocalClass>();

    private static class LocalClass {
        byte[] date = new byte[1 * 1024 * 1024];//1M
    }

    @Override
    public void run() {
        // 多线程执行存在内存泄漏，每次线程执行任务后，并没能结束线程，ThreadLocal若被回收，此时的map中的value就无法访问，造成内存泄漏。
        threadLocal.set(new LocalClass());
        System.out.println(Thread.currentThread().getName()+"->>>"+ threadLocal.get());
//        threadLocal.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =Executors.newFixedThreadPool(1,new ThreadFactory() {
            private AtomicInteger num=new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"TEST"+num.incrementAndGet());
            }
        });
        while (true) {
            executorService.execute(new ThreadLocalStorage3());
        }
    }
}
