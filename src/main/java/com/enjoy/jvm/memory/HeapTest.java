package com.enjoy.jvm.memory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 1/22/2022 8:16 PM
 */
public class HeapTest {

    byte[] a=new byte[1024*100];// 100KB

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> arrayList=new ArrayList<>();
        while (true) {
            arrayList.add(new HeapTest());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
