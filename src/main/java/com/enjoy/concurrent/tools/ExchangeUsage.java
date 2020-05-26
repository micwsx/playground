package com.enjoy.concurrent.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Exchanger应用2个线程之间交换数据结果
 */
public class ExchangeUsage {

    public static void main(String[] args) {
        Exchanger<Set<String>> exchanger=new Exchanger<Set<String>>();
        new Thread(()->{
            Set<String> setA=new HashSet<>();
            setA.add("语文");
            setA.add("数学");
            setA.add("英语");
            System.out.println(Thread.currentThread().getName()+" 设置完成"+setA);
            try {
                setA=exchanger.exchange(setA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 交换后结果："+setA);
        },"[中文]").start();

        new Thread(()->{
            Set<String> setB=new HashSet<>();
            setB.add("Chinese");
            setB.add("Mathematics");
            setB.add("English");
            System.out.println(Thread.currentThread().getName()+" 设置完成"+setB);
            try {
                setB=exchanger.exchange(setB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 交换后结果："+setB);
        },"[English]").start();
    }
}
