package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;

import java.util.Random;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义锁，支持可重入（递归）
 */
public class RecursiveLock {

    private Syn syn = new Syn();

    public void lock() {
        syn.acquire(1);
    }

    public void unLock() {
        syn.release(1);
    }

    private static class Syn extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (Thread.currentThread() == getExclusiveOwnerThread()) {
                int newAcquire = getState() + arg;
                setState(newAcquire);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            int newAcquire = getState() - arg;
            boolean free=false;
            if (newAcquire == 0) {
                free=true;
                setExclusiveOwnerThread(null);
            }
            setState(newAcquire);
            return free;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    private static class TestRecursiveLock {
        private int count = 0;
        private RecursiveLock recursiveLock = new RecursiveLock();

        public void callSelf() {
            recursiveLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获得锁"+ (++count));
                if (count == 5) {
                    System.out.println("达到目标结果 count:" + count);
                } else {
                    SleepUtil.sleep(new Random().nextInt(5) * 100);
                    System.out.println(count);
                    callSelf();
                }
            } finally {
                System.out.println(Thread.currentThread().getName() + " 释放锁");
                recursiveLock.unLock();
            }
        }
    }


    public static void main(String[] args) {
        TestRecursiveLock testRecursiveLock = new TestRecursiveLock();

        new Thread(() -> {
            testRecursiveLock.callSelf();
        }).start();
    }
}
