package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 类锁和对象类
 * 类锁和对象类可以并行执行，所以存在线程安全问题。
 */
public class Crocodile {

    private static int count=0;

    public synchronized void crawl() {
        System.out.println("Crocodile is crawling."+(++count));
    }

    public synchronized void cEat() {
        System.out.println("Crocodile is eating."+(++count));
    }


    public static synchronized void sFeed() {
        System.out.println("Feeding Crocodiles."+(++count));
    }

    public static synchronized void shower() {
        System.out.println("Taking a shower for Crocodiles."+(++count));
    }

    public static void main(String[] args) {

        final Crocodile crocodile = new Crocodile();

        new Thread(() -> {
            int i=100;
            do {
                SleepUtil.sleep(1);
//                crocodile.crawl();
                crocodile.sFeed();
                i--;
            }while(i>0);
        }).start();

        new Thread(() -> {
            int i=100;
            do {
                SleepUtil.sleep(2);
                crocodile.cEat();
//                crocodile.shower();
                i--;
            }while(i>0);
        }).start();




    }

}




