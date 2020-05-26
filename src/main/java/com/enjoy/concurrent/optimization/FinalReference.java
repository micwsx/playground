package com.enjoy.concurrent.optimization;

public class FinalReference {
    final int[] intArray;       // final 是引用类型
    static FinalReference obj;

    public FinalReference() {   // 构造函数
        intArray = new int[1];         // 1
        intArray[0] = 1;               // 2
    }

    public static void writerOne() {   // 写线程A执行
        obj = new FinalReference();// 3
        System.out.println(Thread.currentThread().getName()+" 结果："+ obj);
    }

    public static void writeTwo() {     // 写线程B执行
        obj.intArray[0] = 2;            // 4
        System.out.println(Thread.currentThread().getName()+" 结果："+ obj.intArray[0]);
    }

    public static void reader() {       // 读线程C执行
        if (obj != null) {              // 5
            int temp1 = obj.intArray[0];// 6
            System.out.println(Thread.currentThread().getName()+" 结果："+temp1);
        }
    }

    public static void main(String[] args) {
        FinalReference finalReference = new FinalReference();

        Thread initThread = new Thread(() -> {
            finalReference.writerOne();
        }, "initThread");
        initThread.start();
        // 确保对象先赋值
        try {
            initThread.join();


            Thread writeThread = new Thread(() -> {
                finalReference.writeTwo();
            }, "writeThread");
            writeThread.start();

            writeThread.join();

            new Thread(() -> {
                finalReference.reader();
            }, "reader").start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}
