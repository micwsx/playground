package com.enjoy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * @Author wu
 * @Date 4/1/2021 11:21 AM
 * @Version 1.0
 * 分布式锁实现,这种锁只能是在同个机器上。
 * 如果不同机器，则些类wait与notify,不起使用，在执行redis操作时A机器操作成功，但B机器操作失败，这样的话，A机器notify是无法唤醒B机器中的线程的。
 * 这种方式是行不通。
 */
public class DistributedLock implements Lock {

    private static final String host = "192.168.1.111";
    private static final String password = "p@ssw0rd";
    private static final int port = 6379;
    private static final int connectionTimeout = 10000;
    private static JedisPool jedisPool;
    private static String LOCKER_NAME = "DISTRIBUTED_LOCKER";
    // 分布式锁最好有一个过期时间，防止deadlock.
    private static int LOCKER_EXPIRY_TIME = 5;
    // 保存锁的值
    private ThreadLocal<String> lockValueThreadLocal = new ThreadLocal<>();
    private Thread current;

    static {
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(100);
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, host, port, connectionTimeout, password);
        }
    }

    @Override
    public void lock() {
        try {
            synchronized (this) {
                while (!tryLock()) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        lock();
    }

    @Override
    public boolean tryLock() {
        try {
            tryLock(LOCKER_EXPIRY_TIME, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (current != null && current == Thread.currentThread()) {
            return true;
        } else {
            Jedis jedis = null;
            try {
                // set key
                jedis = jedisPool.getResource();
                String lockValue = UUID.randomUUID().toString();

                String result = jedis.set(LOCKER_NAME, lockValue, "NX", "PX", unit.toMillis(time));
                if (result.equalsIgnoreCase("OK")) {
                    //  success
                    lockValueThreadLocal.set(lockValue);
                    current = Thread.currentThread();
                    System.out.println(current.getName() + "获取锁");
                    return true;
                }
            } finally {
                if (jedis != null)
                    jedis.close();
            }
        }
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this) {
            Jedis jedis = null;
            try {
                if (lockValueThreadLocal.get() == null || current == null)
                    return;
                // delete key
                jedis = jedisPool.getResource();
                String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                jedis.eval(script, Arrays.asList(LOCKER_NAME), Arrays.asList(lockValueThreadLocal.get()));
                lockValueThreadLocal.remove();
                System.out.println(current.getName() + "释放锁");
            } finally {
                if (jedis != null)
                    jedis.close();
                notifyAll();
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
