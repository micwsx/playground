package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

/**
 * start,sleep,interrupt,isInterrupted,join,yield,wait,notify
 * 通过Thread.currentThread().isInterrupted()对象方法或者Thread.interrupted()静态方法都可以判断当前线程是否中断，
 * Thread.currentThread().isInterrupted()中断后标识位不会重新设置
 * Thread.interrupted()中断后标识位会重置为false。
 * 如果有sleep中被interrupt则会抛出异常，中断标识位都会重置为false.
 * interrupt只是通知线程需要中断，但程序可以完全不矛理会。
 * 如果有sleep中被interrupt则会抛出异常，中断标识位重置为false。
 */
public class Egret implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        // 打印线程状态
        System.out.println(threadName + "-" + Thread.currentThread().getState() + "-" + (((!Thread.currentThread().isInterrupted()) == true) ? " not interrupted" : " interrupted"));
        // 判断当前线程是否中断
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(threadName + " is running...");
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
                // 在sleep过程中被interrupt会抛异常并立即将线程中断标识位清除重新设置为false
                System.out.println(threadName + " something happened.");
                // 需要手动再次interrupt
                Thread.currentThread().interrupt();
            }
        }
        // 最后打印线程状态
        System.out.println(threadName + "-" + Thread.currentThread().getState() + "-" + (((!Thread.currentThread().isInterrupted()) == true) ? " not interrupted" : " interrupted"));
    }

    public static void main(String[] args) throws Throwable {

//        Thread egret = new Thread(new Egret());
//        egret.start();
//        SleepUtil.sleep(10);
//        egret.interrupt();

//        Swan swan = new Swan();
//        swan.start();
//        SleepUtil.sleep(10);
//        swan.interrupt();
        Swallow swallow = new Swallow();
        swallow.start();
        SleepUtil.sleep(10);
        swallow.interrupt();
        System.out.println("main end");
    }
}

/**
 * 也可以通过Thread.interrupted()静态方法判断当前线程是否中断，中断后标识位会重置为false,
 * 如果有sleep中被interrupt则会抛出异常，中断标识位重置为false.
 */
class Swan extends Thread {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getState() + "-" + (Thread.interrupted() == true ? "interrupted" : "not interrupted"));
        // 通过静态方法判断当前线程是否中断
        while (!Thread.interrupted()) {
            System.out.println("Swan is running...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Swan something happened.");
                // 需要手动再次interrupt
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getState() + "-" + (Thread.interrupted() == true ? "interrupted" : "not interrupted"));
    }
}

/**
 * interrupt只是通知线程需要中断，但程序可以完全不矛理会。
 * 如果有sleep中被interrupt则会抛出异常，中断标识位重置为false.
 */
class Swallow extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getState() + "-" + (Thread.interrupted() == true ? "interrupted" : "not interrupted"));
        while (true) {
            System.out.println("Swallow is running...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Swallow something happened.");
                System.out.println(Thread.currentThread().getState() + "-" + ( Thread.currentThread().isInterrupted() == true? "interrupted" : "not interrupted"));
            }
        }
    }
}

