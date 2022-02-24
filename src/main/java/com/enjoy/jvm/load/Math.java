package com.enjoy.jvm.load;

/**
 * @author: Michael
 * @date: 1/17/2022 9:25 PM
 * 反编译Math.class文件JVM汇编内容并保存结果到Math.txt
 * javap -c Math.class > Math.txt
 * 编译Java类文件生成Math.class字节码文件
 * javac Math.java
 */
public class Math {

    public static int initData = 666;
    public static Animal animal = new Animal();

    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math=new Math();
        math.compute();
    }
}
