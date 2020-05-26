package com.enjoy.concurrent.container;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet:大多数读，偶尔写。写的时候都临时创建一个对象，最后一起提交更新真正数据。
 */
public class CopyOnWriteArraySetUsage {

    public static void main(String[] args) {
        CopyOnWriteArraySet copyOnWriteArraySet=new CopyOnWriteArraySet<>();

    }

}
