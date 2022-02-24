package com.enjoy.jvm.memory;

import com.enjoy.jvm.load.User;

/**
 * @author: Michael
 * @date: 1/22/2022 9:43 PM
 * 类有可能在栈上分配
 * -XX:+DoEscapeAnalysis默认开启
 * -XX:+EliminateAllocations默认开启
 * 逃逸分析：对象没有逃逸，会分配在栈上。
 * 标量替换：线程栈上没有连续的空间分配给对象(空间是零散的)，将对象成员分散存储，但会标记是同一对象数据。
 *
 * 使用如下参数不会发生GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+ElimiateAllocations
 * 使用如下参数会发生大量GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-ElimiateAllocations
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+ElimiateAllocations
 *
 */
public class AllocationOnStack {
    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        for (int i = 0; i < 100_000_000; i++) {
            alloc();
        }

        long end=System.currentTimeMillis();
        System.out.println(end-start);

    }

    private static void alloc(){
        User user=new User();
        user.setId(1);
        user.setName("michael");
    }
}
