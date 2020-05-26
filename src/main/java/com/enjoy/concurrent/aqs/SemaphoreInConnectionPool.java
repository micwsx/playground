package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 利用Semaphore限制线程同时获取和归还连接池个数
 * 不用对连接池加锁，每次获取连接的线程数不会超过3个。
 */
public class SemaphoreInConnectionPool {

    private static final int CAPACITY = 3;
    // 只有3个连接
    private final LinkedList<Connection> pool = new LinkedList<>();

    // 只有3个许可证
    private Semaphore semaphore = new Semaphore(CAPACITY);

    public SemaphoreInConnectionPool() {
        for (int i = 0; i < CAPACITY; i++) {
            pool.add(new SqlConnection());
        }
    }

    // 拿连接
    public Connection take() {
        Connection connection = null;
        try {
            semaphore.acquire(1);
            System.out.println(Thread.currentThread().getName() + "开始许可证");
            connection = pool.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 释放连接
    public void release(Connection connection) {
        pool.addLast(connection);
        System.out.println(Thread.currentThread().getName() + "释放许可证");
        semaphore.release(1);
    }

    public static void main(String[] args) {
        SemaphoreInConnectionPool pool = new SemaphoreInConnectionPool();
        // 10个线程同时获取数据库连接池是否有问题？
        for (int i = 1; i <= 10; i++) {
            final int num = i;
            new Thread(() -> {
                Connection connection = pool.take();
                try {
                    SleepUtil.sleep(new Random().nextInt(5) * 1000);
                    System.out.println(Thread.currentThread().getName() + " 任务No." + num + " 获取连接" + connection);
                } finally {
                    pool.release(connection);
                }
            }).start();
        }

    }

}
