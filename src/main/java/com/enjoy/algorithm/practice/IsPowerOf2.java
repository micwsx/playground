package com.enjoy.algorithm.practice;

/**
 * @Author wu
 * @Date 3/27/2021 6:46 PM
 * @Version 1.0
 * 判断是否是2的幂数
 */
public class IsPowerOf2 {

    public static boolean isPowerOf2(int a) {
        int newValue = a - 1;
        int result = a & newValue;
        return result == 0;
    }

    public static void main(String[] args) {
        int a = 64;
        boolean powerOf2 = isPowerOf2(a);
        System.out.println(powerOf2);
    }
}
