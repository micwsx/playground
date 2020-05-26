package com.enjoy.concurrent.container;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap是一个跳表的Map
 * 跳表是一个链表，但是通过使用“跳跃式”查找的方式使得插入、读取数据时复杂度变成了O（logn）。
 */
public class ConcurrentSkipListMapUsage {

    public static void main(String[] args) {

        ConcurrentSkipListMap<String,String> concurrentSkipListMap=new ConcurrentSkipListMap<String,String>();
        concurrentSkipListMap.put("English","英文");
        System.out.println(concurrentSkipListMap.get("English"));


    }
}
