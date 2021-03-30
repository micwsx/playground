package com.enjoy.concurrent.blockingQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue：一个延迟队列
 * BlockQueue接口，支持非阻塞式add/remove,offer/poll,(put/take阻塞方式)
 * 阻塞的元素变动方法，插入和移除操作。插入满时阻塞，移除空时阻塞。
 */
public class DelayQueueUsage {

    public static void main(String[] args) {
        DelayQueue<Order<String>> delayQueue = new DelayQueue();
//        delayQueue.add()

        // 5s过期
       // delayQueue.add(new Order<String>(0, "Michael"));
        // 3s过期
        delayQueue.add(new Order<String>(5, "Jack"));

        // 开户1个线程
        new Thread(() -> {
            while (delayQueue.size() > 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 开始获取订单");
                    Order<String> order = delayQueue.take();
                    System.out.println(Thread.currentThread().getName() + " 获取订单结果为： " + order.getData());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }, "[Thread]").start();


    }
}

class Order<T> implements Delayed {

    private long expiryTime;
    private T data;

    public Order(long expiryTime, T data) {
        this.expiryTime = expiryTime * 1000 + System.currentTimeMillis();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(expiryTime - System.currentTimeMillis(), unit);
        System.out.println(d);
        return d;
    }

    @Override
    public int compareTo(Delayed o) {
        long d = o.getDelay(TimeUnit.MILLISECONDS) - this.getDelay(TimeUnit.MILLISECONDS);
        if (d == 0) {
            return 0;
        } else if (d > 0) {
            return 1;
        } else {
            return -1;
        }

    }
}
