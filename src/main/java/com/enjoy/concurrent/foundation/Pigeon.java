package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

/**
 * example for: sleep,start method
 * start,sleep,interrupt,isInterrupted,join,yield,wait,notify
 */
public class Pigeon extends Thread{

    public Pigeon(String name) {
        super(name);
    }

    @Override
    public void run() {
        SleepUtil.sleep(100);
        System.out.println(getName()+" runs the Task.");
    }

    public static void main(String[] args) throws Throwable{
        Pigeon pigeon=new Pigeon("Pigeon");
        pigeon.start();

        SleepUtil.sleep(10);
        System.out.println("main end");
    }

}
