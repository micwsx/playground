package com.enjoy.concurrent.optimization;

import com.enjoy.SleepUtil;

/**
 *
 */
public class FinalAnatomy {
    private int i;                      // 普通变量
    private final int j;                // final变量
    static FinalAnatomy obj;

    public FinalAnatomy() {    // 构造函数
        i = 1;                 // 写普通域
        j = 2;                 // 写final域
    }

    public static void writer() {// 写线程A执行
        obj = new FinalAnatomy();
    }

    public static void reader() {   // 读线程B执行
        FinalAnatomy object = obj;  // 读对象引用
        int a = object.i;           // 读普通域
        int b = object.j;           // 读final域
        System.out.println("a=" + a + "-b=" + b);
    }

    public static void main(String[] args) {
        FinalAnatomy finalAnatomy = new FinalAnatomy();

        Thread writerThread = new Thread(() -> {
            System.out.println("初始化");
            finalAnatomy.writer();
        }, "Michael");
        writerThread.start();

        // 确保对象先赋值
        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            finalAnatomy.reader();
        }, "Leo").start();

    }
}
