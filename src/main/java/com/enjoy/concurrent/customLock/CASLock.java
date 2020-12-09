package com.enjoy.concurrent.customLock;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Michael
 * @create 11/22/2020 7:46 PM
 * 模拟锁-实现有两种(自旋-没有抢到锁就一直循环获取锁和信号量-没有抢到锁进行休眠）。
 * 自旋方式
 */
public class CASLock {

    private volatile int state;
    public static long stateOffset;
    public static Unsafe unsafe = getUnsafe();

    public final int getState() {
        return state;
    }

    public final void setState(int state) {
        this.state = state;
    }

    public static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(CASLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * CAS一直循环获取锁。
     *
     * @return
     */
    public boolean tryLock() {
        // this, 变量内存地址，期望值，最新值。
        while (!unsafe.compareAndSwapInt(this, stateOffset, 0, 1)) {
//            ReentrantLock lock = new ReentrantLock();
//            lock.lock();
//            lock.unlock();
        }
        return true;
    }

    /**
     * 释放锁
     */
    public void unLock() {
        if (state == 1) {
            state--;
        }
    }

}