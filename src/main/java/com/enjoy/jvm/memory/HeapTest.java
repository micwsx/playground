package com.enjoy.jvm.memory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 1/22/2022 8:16 PM
 *
 * -XX:+PrintGCDetails -Xms90M -Xmx90M -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8
 */
public class HeapTest {

    byte[] a=new byte[1024*100];// 100KB

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> arrayList=new ArrayList<>();
        while (true) {
            arrayList.add(new HeapTest());
            TimeUnit.MILLISECONDS.sleep(700);
        }
    }
}
