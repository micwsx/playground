package com.enjoy.jvm.memory;

import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 1/30/2022 3:10 PM
 * -Xmn100m -Xms300m -Xmx300m -XX:MaxMetaspaceSize=200m -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8
 */
public class DeadLockSample {

    private static Object lock1=new Object();
    private static Object lock2=new Object();

    public static void main(String[] args) {

       new Thread(()->{

           synchronized (lock1){
               System.out.println(Thread.currentThread().getName()+" get lock1 and wait lock2...");
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (lock2){
                   System.out.println(Thread.currentThread().getName()+" get lock1 and lock2.");
               }
           }

        }).start();

        new Thread(()->{
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+" get lock2 and wait lock1...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+" get lock1 and lock2.");
                }
            }
        }).start();


    }

}
