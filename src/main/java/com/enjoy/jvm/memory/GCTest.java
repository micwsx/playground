package com.enjoy.jvm.memory;

/**
 * @author: Michael
 * @date: 1/22/2022 9:54 PM
 * 添加jvm参数-XX:+PrintGCDetails
 * 查看JVM内存使用情况
 *
 * 大对象大小直接进入老年代:只支持Serial和ParNew两种收集器
 * -XX:PretenureSizeThreshold=10000(单位字节) -XX:+UseSerialGC
 * 长期存活的对象进入老年代
 * -XX:MaxTenuringThreshold来设置
 */
public class GCTest {
    public static void main(String[] args) {
        byte[] a=new byte[60000*1024];//60M
        byte[] b=new byte[8000*1024];//8M

        byte[] c=new byte[1000*1024];//1M
        byte[] d=new byte[1000*1024];//1M
        byte[] e=new byte[1000*1024];//1M
        byte[] f=new byte[1000*1024];//1M

    }
}
