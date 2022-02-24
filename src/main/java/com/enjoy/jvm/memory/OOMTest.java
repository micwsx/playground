package com.enjoy.jvm.memory;

import com.enjoy.jvm.load.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: Michael
 * @date: 1/30/2022 3:02 PM
 *
 * -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\jvm.dump
 */
public class OOMTest {
    public static List<Object> list=new ArrayList<>();

    public static void main(String[] args) {
        List<Object> list=new ArrayList<>();
        int i=0;
        int j=0;
        while (true){
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j++, UUID.randomUUID().toString());
        }
    }
}
