package com.enjoy.concurrent.customThreadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Michael
 * @create 12/7/2020 4:50 PM
 */
public class TaskQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private int capacity;

    private ReentrantLock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();
    private PolicyHandler handler;
    private CustomThreadPool pool;
    // 存放任务链表集合
    private Deque<MyTask> list;

    public TaskQueue(int capacity, CustomThreadPool pool, PolicyHandler handler) {
        this.capacity = capacity;
        list = new ArrayDeque<>(capacity);
        this.handler = handler;
        this.pool = pool;
    }


    public void putWithHandler(MyTask task) {
        lock.lock();
        try {
            while (list.size() == capacity) {
                logger.info("任务队列满了，任务" + task.getName() + ",阻塞等待...");
                handler.handle(this.pool, task);
            }
            logger.info("任务" + task.getName() + "成功添加到任务队列中");
            list.add(task);
            emptyCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void put(MyTask task) {
        lock.lock();
        try {
            while (list.size() == capacity) {
                logger.info("任务队列满了，任务" + task.getName() + ",阻塞等待...");
                fullCondition.await();
            }
            logger.info("任务" + task.getName() + "成功添加到任务队列中");
            list.add(task);
            emptyCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public MyTask poll() {
        lock.lock();
        try {
            while (list.size() <= 0) {
                logger.info("任务队列为空，阻塞等待...");
                emptyCondition.await();
            }
            MyTask myTask = list.poll();
            logger.info("成功拿出任务" + myTask.getName());
            fullCondition.signalAll();
            return myTask;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    public MyTask poll(long timeout) {
        lock.lock();
        try {
            while (list.size() <= 0) {
                logger.info("任务队列为空，阻塞等待...");
                if (emptyCondition.await(timeout, TimeUnit.SECONDS)||list.isEmpty()) {
                    logger.info("一直没有任务，获取任务超时");
                    return null;
                }
            }
            MyTask myTask = list.poll();
            logger.info("成功拿出任务" + myTask.getName());
            fullCondition.signalAll();
            return myTask;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
