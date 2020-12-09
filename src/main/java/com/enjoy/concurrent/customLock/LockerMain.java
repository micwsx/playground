package com.enjoy.concurrent.customLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael
 * @create 11/22/2020 7:36 PM
 * 创建自己的锁并检测锁。
 */
public class LockerMain {

    private static int sum = 0;
    private static CASLock locker = new CASLock();
    private static ParkLock parkLock = new ParkLock();
    private static SyncLock syncLock = new SyncLock();
    private static final Logger logger = LoggerFactory.getLogger(LockerMain.class);

    public static void main(String[] args) {

        Thread[] threads = new Thread[2];

        threads[0] = new Thread(() -> {
//            taskWithoutLock();
//            taskWithCASLock();
            taskWithLock();
        });
        threads[1] = new Thread(() -> {
//            taskWithoutLock();
//            taskWithCASLock();
            taskWithLock();
        });
        threads[0].start();
        threads[1].start();

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("计算结果：" + sum);
    }

    private static void taskWithoutLock() {
        logger.info(Thread.currentThread().getName() + "获取锁");
        Random random = new Random();
        for (int j = 0; j < 200; j++) {
            sleep(random.nextInt(10));
            sum += 1;
            logger.info(Thread.currentThread().getName() + ":" + sum);
        }
    }

    private static void taskWithLock() {
        syncLock.lock();
        try {
            Random random = new Random();
            logger.info(Thread.currentThread().getName() + "获取锁");
            for (int j = 0; j < 100; j++) {
                sleep(random.nextInt(10));
                sum += 1;
                logger.info(Thread.currentThread().getName() + ":" + sum);
            }
        } finally {
            logger.info(Thread.currentThread().getName() + "释放锁");
            syncLock.unlock();
        }

    }

    private static void taskWithParkLock() {
        try {
            if (parkLock.tryLock()) {
                Random random = new Random();
                logger.info(Thread.currentThread().getName() + "获取锁");
                for (int j = 0; j < 100; j++) {
                    sleep(random.nextInt(10));
                    sum += 1;
                    logger.info(Thread.currentThread().getName() + ":" + sum);
                }
            }
        } finally {
            logger.info(Thread.currentThread().getName() + "释放锁");
            parkLock.unLock();
        }
    }

    private static void taskWithCASLock() {
        try {
            if (locker.tryLock()) {
                Random random = new Random();
                logger.info(Thread.currentThread().getName() + "获取锁");
                for (int j = 0; j < 100; j++) {
                    sleep(random.nextInt(10));
                    sum += 1;
                    logger.info(Thread.currentThread().getName() + ":" + sum);
                }
            }
        } finally {
            logger.info(Thread.currentThread().getName() + "释放锁");
            locker.unLock();
        }
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public class MutexLock {


    }
}
