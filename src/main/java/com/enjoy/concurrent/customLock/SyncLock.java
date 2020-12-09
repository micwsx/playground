package com.enjoy.concurrent.customLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * @author Michael
 * @create 12/7/2020 12:55 PM
 * 通过aqs创建锁
 */
public class SyncLock implements Lock {
    private static final Logger logger = LoggerFactory.getLogger(SyncLock.class);
    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquire(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 可以借鉴ReentrantLock实现
     */
    public class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            final Thread current = Thread.currentThread();
            if (current == getExclusiveOwnerThread()) {
                // 是自己则只累加
                int state = getState() + arg;
                if (getState() < 0)
                    throw new RuntimeException("Maximum lock count exceeded");
                setState(state);
                return true;
            } else {
                // 不是自己
                //cas设置锁状态
                if (super.compareAndSetState(0, arg)) {
                    // 设置成功
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            return false;
        }

        public boolean tryAcquire(int arg, long timeout) {
            long expiryTime = System.nanoTime() + timeout;
            long waitTime = timeout;
            while (!tryAcquire(1)) {
                if (waitTime > 0) {
                    logger.info(Thread.currentThread().getId() + "被阻塞" + waitTime + "，获取锁失败");
                    LockSupport.parkNanos(waitTime);
                } else {
                    logger.info(Thread.currentThread().getId() + "等待超时，获取锁失败");
                    return false;
                }
                waitTime = expiryTime - System.nanoTime();
            }
            return true;
        }


        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (state == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(state);
            return free;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        public final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    public static void main(String[] args) {
        // 测试获取锁超时
        SyncLock syncLock = new SyncLock();

        Thread t1 = new Thread(() -> {
            try {
               if( syncLock.tryLock(2, TimeUnit.SECONDS)){
                   logger.info(Thread.currentThread().getId() + "获得锁，等待10秒...");
                   TimeUnit.SECONDS.sleep(10);
                   logger.info(Thread.currentThread().getId() + "打印信息");
                   syncLock.unlock();
                   logger.info(Thread.currentThread().getId() + "释放锁");
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        try {
            logger.info("主线程等待2秒");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            try {
                logger.info(Thread.currentThread().getId() + "尝试获得锁");
                if (syncLock.tryLock(2, TimeUnit.SECONDS)){
                    logger.info(Thread.currentThread().getId() + "获得锁，打印信息");
                    syncLock.unlock();
                    logger.info(Thread.currentThread().getId() + "释放锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
