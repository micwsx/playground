package com.enjoy.net.nio;

import com.sun.management.OperatingSystemMXBean;
import sun.nio.ch.DirectBuffer;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael
 * @create 1/25/2021 1:18 PM
 * HeapByteBuffer: Java Heap上分配内存，由JAVA虚拟机托管管理。分配内存空间快，但多了拷贝过程，操作慢。
 * DirectByteBuffer: -XX:MaxDirectMemorySize(默认64M)堆外内存分配，与磁盘建立映射。堆外分配内存慢，但操作过程快。
 * 》》不建议使用DirectByteBuffer是因为由系统full gc控制，自己检测情况而调用system.gc()。其实自己可通过clean方法释放内存（见以下示例代码）。
 */
public class NIOBuffer {
    public static void main(String[] args) {
        try {
            showPhysicalSpace();
            //HeapByteBuffer
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            //DirectByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024 * 1);//1G
            System.out.println("start");
            TimeUnit.SECONDS.sleep(5);
            showPhysicalSpace();
            ((DirectBuffer) byteBuffer).cleaner().clean();//可以查看JAVA堆内存没有变化，但是清理后物理内存是有明显变化。
            System.out.println("end");
            TimeUnit.SECONDS.sleep(5);
            showPhysicalSpace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void showPhysicalSpace(){
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        long freePhysicalMemorySize = operatingSystemMXBean.getFreePhysicalMemorySize();
        System.out.println("current size: "+freePhysicalMemorySize);
    }
}
