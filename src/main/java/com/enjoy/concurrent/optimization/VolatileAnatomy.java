package com.enjoy.concurrent.optimization;

import com.enjoy.SleepUtil;

/**
 * Volatile关键字解剖，flag变量修改后，i的值也会刷新。
 */
public class VolatileAnatomy {

    private int i = 1;
    private volatile boolean flag = false;

    public void setValue(int i) {
        this.i = i;
        flag = true;
        System.out.println("设置完成");
    }

    public void read() {
        while (!flag) {
            System.out.println("等待修改：" + "flag=" + flag + "- i=" + i);
        }
        System.out.println("结束" + "flag=" + flag + "- i=" + i);
    }

    public static void main(String[] args) {

        VolatileAnatomy volatileAnatomy=new VolatileAnatomy();
        new Thread(()->{
            System.out.println("等待5s后开始修改变量值。。。");
            SleepUtil.sleep(2000);
            volatileAnatomy.setValue(100);
        },"Michael").start();

        new Thread(()->{
            volatileAnatomy.read();
        },"Leo").start();


    }


}
