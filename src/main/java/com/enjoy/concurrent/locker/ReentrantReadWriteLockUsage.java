package com.enjoy.concurrent.locker;

import com.enjoy.SleepUtil;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 - 读锁共享，写锁独占，读写互斥。
 */
public class ReentrantReadWriteLockUsage {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // read lock
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    // wirte lock
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private int age;

    public int getAge() {
        readLock.lock();
        try {
            SleepUtil.sleep(200);
            System.out.println(Thread.currentThread().getName() + " 进入读代码区");
            return age;
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " 退出读代码区");
        }
    }

    public void setAge(int age) {
        writeLock.lock();
        try {
            SleepUtil.sleep(500);
            System.out.println(Thread.currentThread().getName() + " 准备写代码区");
            this.age = age;
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " 退出写代码区");
        }
    }

    public static void main(String[] args) {

        ReentrantReadWriteLockUsage usage=new ReentrantReadWriteLockUsage();
        // 2个线程每个线程读10次
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    usage.getAge();
                }
            },"Michael["+i+"]").start();
        }
        // 1个线程写5次
        new Thread(()->{
            for (int j = 0; j < 5; j++) {
                usage.setAge(new Random().nextInt(100));
            }
        },"Leo").start();


    }
}
