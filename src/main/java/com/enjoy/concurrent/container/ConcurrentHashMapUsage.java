package com.enjoy.concurrent.container;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发容器实现机制：Synchronized加锁和CAS操作保证多线程并发执行。
 *
 * ConcurrentHashMap一个Map
 * 1.7 Segment分段锁，1.第一次hash找到哪个Segment，2.第二次hash确定key在哪个链表中，第三步在链表中对比key相等的结点。
 * 读并不会加锁，只有在put,remove操作时才加锁，加锁只针对操作的segment,并不影响其它segment的读写，不同的segment可以并发使用，提高性能。
 * 1.8 去掉Segment使用链表和红黑树结构切换
 */
public class ConcurrentHashMapUsage {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("English", "英文");
        concurrentHashMap.put("Chinese", "中文");
//        System.out.println(concurrentHashMap.get("English"));//中文
//        String value = concurrentHashMap.putIfAbsent("Chinese", "123");//123
//        value = concurrentHashMap.putIfAbsent("Chinese", "456");//123
//        System.out.println(concurrentHashMap.get("Chinese"));//123
    }
}
