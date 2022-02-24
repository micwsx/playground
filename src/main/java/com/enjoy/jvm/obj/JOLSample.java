package com.enjoy.jvm.obj;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Michael
 * @date: 1/30/2022 2:13 PM
 * 对象=对象头+实例数据+填充
 * 对象头=
 * mark word标记字段（32位占4个字节，64位占8个字节） +
 * klass point类型指针(开启指针压缩占4字节，关闭压缩占8字节)类的元数据指针 +
 * 数组长度(只有数组对象才有)
 * -XX:+UseCompressOops默认开启(klass point和成员变量都会压缩)
 * -XX:+UseCompressClassPointers默认开启只会压缩klass point
 */
public class JOLSample {
    public static void main(String[] args) {
        // object
        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());

        System.out.println();

        // array
        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());

        System.out.println();
        // A
        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2.toPrintable());
    }

    private static class A{
        int id; //4
        String name;//
        byte b; //1
        Object o;//
    }
}
