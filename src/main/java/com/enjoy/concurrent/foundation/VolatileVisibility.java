package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

/**
 * 当变量使用的volatile关键字，其它线程读取变量值的时候，会将线程本地私有内存转为失效，重新将主内存中的值刷新到本地内存中。
 * 多用于一写多读的应用场景，属于轻量及的线程同步机制。
 */
public class VolatileVisibility {
    // 最好是加上volatile关键字
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        VolatileVisibility volatileVisibility = new VolatileVisibility();
        new Thread(() -> {
            while (!volatileVisibility.isFlag());
//            while (!volatileVisibility.isFlag()){
//                SleepUtil.sleep(1);
//                System.out.println("fddddd");
//            }
            System.out.println(volatileVisibility.isFlag());
        }).start();

        new Thread(() -> {
            SleepUtil.sleep(20);
            volatileVisibility.setFlag(true);
        }).start();


    }
}
