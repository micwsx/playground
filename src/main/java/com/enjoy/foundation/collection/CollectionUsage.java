package com.enjoy.foundation.collection;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Author wu
 * @Date 3/23/2021 4:34 PM
 * @Version 1.0
 */
public class CollectionUsage {

    public static void main(String[] args) {
        List vector = new Vector();
        while (true) {
            for (int i = 0; i < 10; i++)
                vector.add(i);
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        Object remove = vector.remove(i);
                        System.out.println("remove.." + remove);
                    }
                }

                ;
            };
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        Object o = vector.get(i);
                        System.out.println("get.." + o);
                    }
                }

                ;
            };
            thread1.start();
            thread2.start();
        }
    }

    public static void listDemo() {
        //array实现
        List list = new ArrayList();
        //节点链表实现
        list = new LinkedList();
        // 线程安全 synchronized+array
        list = new Vector();
        // 内部是有vector实现
        list = new Stack();
        // 并发容器
        list = new CopyOnWriteArrayList();


    }

    public static void mapDemo() {
        // 无序
        Map map = new HashMap();
        // 有顺
        map = new TreeMap();

        map = new LinkedHashMap();
        // 线程安全
        map = new Hashtable();
        // 并发容器
        map = new ConcurrentHashMap();
        map = new ConcurrentSkipListMap();
    }

    public static void setDemo() {
        //HashMap内部实现
        Set set = new HashSet();
        // treeMap内部实现
        set = new TreeSet();
        set = new LinkedHashSet();
        // 并发容器
        set = new ConcurrentSkipListSet();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), null, null);

    }


}
