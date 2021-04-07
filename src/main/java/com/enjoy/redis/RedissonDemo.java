package com.enjoy.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @Author wu
 * @Date 4/1/2021 4:59 PM
 * @Version 1.0
 * https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers
 */
public class RedissonDemo {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:7181");
        RedissonClient redissonClient = Redisson.create(config);
        RLock myLock = redissonClient.getLock("myLock");
        myLock.lock();
        try {
            // or acquire lock and automatically unlock it after 10 seconds
            myLock.lock(10, TimeUnit.SECONDS);




        } finally {
            myLock.unlock();
        }

    }
}
