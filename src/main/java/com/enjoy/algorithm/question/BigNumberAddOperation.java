package com.enjoy.algorithm.question;

import jodd.util.ArraysUtil;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 4/13/2021 10:41 AM
 * @Version 1.0
 * 超大数字相加操作
 * 思路：使用字符串表示2个超大数值，
 * 使用一个数组，长度是=最长字符串的长度+1（有可能有进位）
 */
public class BigNumberAddOperation {


    private static String add(String num1, String num2) {
        int MAX = Math.max(num1.length(), num2.length()) + 1;
        int[] result = new int[MAX];
        int[] bigNumber1 = new int[MAX];
        int[] bigNumber2 = new int[MAX];
        // 分别从2个数组尾部开始一位一位相加
        for (int i = 0; i < num1.length(); i++) {
            bigNumber1[i] = num1.charAt(num1.length() - i - 1) - '0';
        }
        for (int i = 0; i < num2.length(); i++) {
            bigNumber2[i] = num2.charAt(num2.length() - i - 1) - '0';
        }

        int mod = 0;
        for (int i = 0; i < result.length; i++) {
            int sum = bigNumber1[i] + bigNumber2[i] + mod;
            mod = sum / 10;//进位值
            result[i] = sum % 10;
        }
        int[] finalResult = reverseArray(result);
        return concatArray(finalResult);
    }

    private static int[] reverseArray(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - i - 1];
        }
        return newArray;
    }

    private static String concatArray(int[] result) {
        StringBuilder stringBuilder = new StringBuilder();
        if (result[0] != 0)
            stringBuilder.append(result[0]);
        for (int i = 1; i < result.length; i++) {
            stringBuilder.append(result[i]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String bigOne = "123";
        String bigTwo = "856";
        String add = add(bigOne, bigTwo);
        System.out.printf(add);

    }
}
