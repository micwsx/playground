package com.enjoy.concurrent.locker;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  ReetrantLock可重入锁 递归调用
 */
public class RecursiveReentrantLock {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private int age;

    public int addAge() {
        reentrantLock.lock();
        try {
            if (age == 100) {
                return -1;
            } else {
                age++;
                System.out.println("age: "+age);
                addAge();
            }
            return age;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int getAge(){
        reentrantLock.lock();
        try {
            return age;
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        RecursiveReentrantLock task=new RecursiveReentrantLock();
        new Thread(()->{
            task.addAge();
        },"Michael").start();
    }

}
