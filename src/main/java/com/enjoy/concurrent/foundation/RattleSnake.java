package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

/**
 * 设置守护线程setDaemon()方法必须在start()之前
 */
public class RattleSnake implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("RattleSnake is wandering");
        } finally {
            SleepUtil.sleep(5000);
            System.out.println("Be careful!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new RattleSnake());
        // 必须在启动之前设置
        thread.setDaemon(true);
        thread.start();

        //thread.join();
        System.out.println("Main End");

    }

}
