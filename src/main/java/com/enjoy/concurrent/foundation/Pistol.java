package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 通过ArrayBlockingQueue阻塞队列实现装弹匣射击过程
 * 定义10发子弹的手枪
 */
public class Pistol implements Weapon {

    private static final int MAX_CAPACITY = 10;
    // 定义10发子弹
    private static final ArrayBlockingQueue<Bullet> magazine = new ArrayBlockingQueue<Bullet>(MAX_CAPACITY);

    // 装弹
    public void loadBullet() throws Throwable {
        // put会阻塞， add和offer不会阻塞，满则抛出异常
        magazine.put(new Bullet());
        System.out.println("装弹匣：子弹数为" + magazine.size());
    }

    // 射击
    public void fire() throws Throwable {
        // take会阻塞， remove和poll不会阻塞，空则抛出异常
        magazine.take();
        System.out.println("射击后：子弹数为" + magazine.size());
    }

    public static void main(String[] args) throws InterruptedException {

        Pistol pistol = new Pistol();
        Shooter shooter = new Shooter(pistol);
        for (int i = 0; i < 2; i++) {
            new Loader(pistol).start();
        }
        // 2s后开始射击
//        SleepUtil.sleep(2000);
        shooter.start();
    }
}

class Bullet {

}


interface Weapon {
    void loadBullet() throws Throwable;

    void fire() throws Throwable;
}

class Shooter<T extends Weapon> extends Thread {

    private T weapon;

    public Shooter(T weapon) {
        this.weapon = weapon;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 开始射击
                SleepUtil.sleep(1000);
                weapon.fire();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}


class Loader<T extends Weapon> extends Thread {

    private T weapon;

    public Loader(T weapon) {
        this.weapon = weapon;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 开始装弹
                SleepUtil.sleep(500);
                weapon.loadBullet();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
