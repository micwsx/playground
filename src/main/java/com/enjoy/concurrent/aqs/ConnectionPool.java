package com.enjoy.concurrent.aqs;

import com.enjoy.SleepUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义数据库连接池。模拟获取连接（支持等待超时）和释放连接
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList();

    public ConnectionPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            pool.add(new SqlConnection());
        }
    }


    // 获取连接
    public Connection take() {
        Connection connection = null;
        synchronized (pool) {
            while (pool.size() <= 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 等待释放连接。");
                    pool.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            connection = pool.remove();
        }
        return connection;
    }


    // 获取连接，超时后返回null
    public Connection take(long mills) {
        Connection connection = null;
        synchronized (pool) {
            if (mills <= 0) {
                // 一直阻塞等待直到获取
                while (pool.size() <= 0) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                connection = pool.remove();
            } else {
                long expiryTime = mills + System.currentTimeMillis();
                long remain = mills;
                while (pool.isEmpty() && remain >= 0) {
                    try {
                        pool.wait(remain);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    remain = expiryTime - System.currentTimeMillis();
                }
                // 超时或者不为空时，再尝试拿一次连接
                if (!pool.isEmpty())
                    connection = pool.remove();
            }
        }
        return connection;
    }

    // 释放连接
    public void release(Connection connection) {
        synchronized (pool) {
            pool.addLast(connection);
            pool.notifyAll();
        }
    }

    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(2);
        AtomicInteger got = new AtomicInteger(0);
        AtomicInteger notGot = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Connection connection = pool.take(1000);
                if (connection != null) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 拿到连接" + connection + ",次数：" + got.incrementAndGet());
                        SleepUtil.sleep(2000);
                    } finally {
                        pool.release(connection);
                        System.out.println(Thread.currentThread().getName() + " 释放连接：" + connection);
                    }
                } else {
                    System.out.println("没能获取连接数：" + notGot.incrementAndGet());
                }
            }).start();
            SleepUtil.sleep(new Random().nextInt(5) * 10);
        }
    }



}
