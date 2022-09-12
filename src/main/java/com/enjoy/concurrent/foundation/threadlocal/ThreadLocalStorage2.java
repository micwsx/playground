package com.enjoy.concurrent.foundation.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 为每个内存分配一个较小的内存空间，如果分配的对象过大，内存造成内存溢出。
 * ThreadLocal 存储类型必须是引用类型非primitive类型
 */
public class ThreadLocalStorage2 implements Runnable{
    // 局部变量修饰，不影响使用，但每次实例化都要创建对象是没有必要的。
    private ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public void run() {
        Integer integer= threadLocal.get();
        threadLocal.set(integer+1);
        System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
        System.gc();
        System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
    }


}
