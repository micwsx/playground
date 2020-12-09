package com.enjoy.concurrent.customLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Michael
 * @create 11/30/2020 5:15 PM
 * 模拟锁-实现有两种(自旋-没有抢到锁就一直循环获取锁和信号量-没有抢到锁进行休眠）。
 * 信号量方式
 */
public class ParkLock {

    private volatile int state = 0;
    private static long offsetState;
    private static Unsafe unsafe = getUnsafe();

    static {
        try {
            offsetState = unsafe.objectFieldOffset(ParkLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
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

    private List<Thread> threads=new ArrayList<>();

    public boolean tryLock() {
        while (!unsafe.compareAndSwapInt(this, offsetState, 0, 1)) {
            // 阻塞线程
            threads.add(Thread.currentThread());
            LockSupport.park(Thread.currentThread());
        }
        if (threads.contains(Thread.currentThread())){
            threads.remove(Thread.currentThread());
        }
        return true;
    }

    public void unLock() {
        if (state == 1)
            state--;
        for (Thread thread : threads) {
            LockSupport.unpark(thread);
        }
    }


}
