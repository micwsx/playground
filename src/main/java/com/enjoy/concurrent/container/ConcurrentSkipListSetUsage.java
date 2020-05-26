package com.enjoy.concurrent.container;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * ConcurrentSkipListSet
 */
public class ConcurrentSkipListSetUsage {

    public static void main(String[] args) {
        ConcurrentSkipListSet concurrentSkipListSet=new ConcurrentSkipListSet();
        concurrentSkipListSet.add("English");
        System.out.println(concurrentSkipListSet);

    }
}
