package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;

import java.util.Random;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义锁，不支持可重入(NonRecurisiveLock)
 * 自定义锁，支持可重入（RecursiveLock）
 * 自定义锁，共享锁（SharedLock）
 * 自定义线程池（ConnectionPool）
 * 自定义阻塞队列（见Gun实现示例）
 * 死锁（TransferDeadLock示例）
 */
public class NonRecurisiveLock {

    private final Syn syn = new Syn();


    public void lock() {
        syn.acquire(1);
    }

    public void unLock() {
        syn.release(1);
    }

    private class Syn extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int acquire) {
            if (compareAndSetState(0, acquire)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int acquire) {
            int newAcquire = getState() - acquire;
            setState(newAcquire);
            setExclusiveOwnerThread(null);
            return true;

        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }


        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        final boolean isLocked() {
            return getState() != 0;
        }
    }

    private static class TestLock {
        private int count = 0;
        private NonRecurisiveLock nonRecurisiveLock = new NonRecurisiveLock();

        public void incrementSelf() {
            nonRecurisiveLock.lock();
            try {
                if (count == 10) {
                    System.out.println("count:" + count);
                } else {
                    count++;
                    SleepUtil.sleep(new Random().nextInt(5) * 100);
                    System.out.println(count);
                    incrementSelf();
                }
            } finally {
                nonRecurisiveLock.unLock();
            }
        }

        public void add() {
            nonRecurisiveLock.lock();
            try {
                count++;
                SleepUtil.sleep(new Random().nextInt(5) * 10);
                System.out.println(count);
            } finally {
                nonRecurisiveLock.unLock();
            }
        }
    }

    public static void main(String[] args) {

        //callSelf();

        noRecursiveCall();
    }

    private static void callSelf() {
        TestLock testLock = new TestLock();
        new Thread(() -> {
            testLock.incrementSelf();
        }).start();
    }

    private static void noRecursiveCall() {
        TestLock testLock = new TestLock();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                testLock.add();
            }).start();
        }
    }
}
