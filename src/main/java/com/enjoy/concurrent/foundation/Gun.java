package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * wait(),notify()实现多线程等待通知功能
 */
public class Gun implements Weapon {
    private static final int MAX_CAPACITY = 10;
    // 定义10发子弹
    private final Bullet[] magazine = new Bullet[MAX_CAPACITY];
    private int currentPosition = 0;

    // 装弹
    public void loadBullet() throws Throwable {
        synchronized (magazine) {
            while (currentPosition == MAX_CAPACITY) {
                System.out.println("弹匣已满：等待射击!");
                magazine.wait();
            }
            magazine[currentPosition] = new Bullet();
            currentPosition++;
            System.out.println("装弹匣：子弹数为" + currentPosition);
            magazine.notifyAll();
        }
    }

    // 射击
    public void fire() throws Throwable {
        synchronized (magazine) {
            while (currentPosition == 0) {
                System.out.println("弹匣为空：等待装弹匣...");
                magazine.wait();
            }
            currentPosition--;
            magazine[currentPosition] = null;
            System.out.println("射击后：子弹数为" + currentPosition);
            magazine.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Gun gun = new Gun();
        Shooter shooter = new Shooter(gun);
        for (int i = 0; i < 2; i++) {
            new Loader(gun).start();
        }
        // 2s后开始射击
        // SleepUtil.sleep(2000);
        shooter.start();

    }

}
