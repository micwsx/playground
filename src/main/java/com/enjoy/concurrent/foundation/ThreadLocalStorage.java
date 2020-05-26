package com.enjoy.concurrent.foundation;

/**
 * ThreadLocal 为每个内存分配一个较小的内存空间，如果分配的对象过大，内存造成内存溢出。
 * ThreadLocal 存储类型必须是引用类型非primitive类型
 */
public class ThreadLocalStorage implements Runnable{

    private static final ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
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
    }

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new ThreadLocalStorage(),"Michael["+i+"]").start();
        }

    }

}
