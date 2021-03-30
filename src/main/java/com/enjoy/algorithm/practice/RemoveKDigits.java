package com.enjoy.algorithm.practice;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/28/2021 3:33 PM
 * @Version 1.0
 * 移除一个数，使数值最小。从前到后找到第一个数大于它后面的数字，移除就可以了。这样保证是局部最小的，移除多个数字就重复循环移除第一个数字大于它后面数字。
 */
public class RemoveKDigits {

    public static void main(String[] args) {
//        String number = "012345";
        String number = "52345";
        String result = removeKDigits(number, 1);
        System.out.println(result);
    }

    public static String removeKDigits(String number, int nums) {
        String newNum = number;
        char[] chars = newNum.toCharArray();
        boolean hasCut = false;
        for (int j = 0; j < nums; j++) {
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] > chars[i + 1]) {
                    hasCut = true;
                    newNum = newNum.substring(0, i) + newNum.substring(i + 1);
                    break;
                }
            }
        }
        if (!hasCut) {
            // 移除最后一个数
            newNum = newNum.substring(0, newNum.length() - 1);
        }
        String result = removeZero(newNum);

        if (result.length() == 0)
            return "0";

        return result;
    }

    private static String removeZero(String newNum) {
        char c = newNum.charAt(0);
        if (c == '0') {
            return newNum.substring(1);
        }
        return newNum;
    }
}
