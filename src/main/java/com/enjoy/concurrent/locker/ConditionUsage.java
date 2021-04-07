package com.enjoy.concurrent.locker;

import com.enjoy.SleepUtil;
import jodd.util.CsvUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock和Condition实现等待await()/通知signal()/signalAll()功能
 * 等价于Synchronized和wait()，notify()/notifyAll()操作。
 */
public class ConditionUsage {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    private int age;

    public void setAge(int age) {
        SleepUtil.sleep(100);
        reentrantLock.lock();
        try {
            this.age = age;
            System.out.println(Thread.currentThread().getName() + " age: " + age);
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void checkAge() {
        reentrantLock.lock();
        try {
            while (this.age <= 18) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 年龄未达18岁，还有" + (18 - this.age) + "年！");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.age = age;
            System.out.println(Thread.currentThread().getName() + "年龄已达18岁，可以制作身份证！");
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {

        ConditionUsage conditionUsage = new ConditionUsage();

        // 2个线程；1个不断的读，1个不断的写
        new Thread(() -> {
            conditionUsage.checkAge();
        }, "Michael").start();

        try {
            System.out.println("5秒后开始写");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                conditionUsage.setAge(i);
            }
        }, "Leo").start();

    }

}
