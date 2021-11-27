package com.enjoy.algorithm.practice;

/**
 * @Author wu
 * @Date 3/28/2021 6:27 PM
 * @Version 1.0;
 * 2个大整数相加。思路，用字符串逆向存储数值，再每位相加，有进位向前一位加1，最后逆向打印数组即得出结果。
 */
public class BigNumberSum {

    public static void main(String[] args) {

//        String a = "12345";
//        String b = "12345";
        String a = "232435436536543654365436536365436543";
        String b = "232435436536543654365436536365436543";
        String s = sumBigNumbers(a, b);
        System.out.println(s);

    }

    private static String sumBigNumbers(String a, String b) {
        int len = Math.max(a.length(), b.length());
        int[] bigNumberA = new int[len + 1];// 最大长度加1，可能有进位,逆序存储大数值
        int[] bigNumberB = new int[len + 1];//最大长度加1，可能有进位,逆序存储大数值
        for (int i = 0; i < a.length(); i++) {
            bigNumberA[i] = a.charAt(a.length() - i - 1) - '0';
        }
        for (int i = 0; i < b.length(); i++) {
            bigNumberB[i] = b.charAt(b.length() - i - 1) - '0';
        }

        int[] result = new int[len + 1];// 最大长度加1，可能有进位

        for (int i = 0; i < result.length; i++) {
            int sum = bigNumberA[i] + bigNumberB[i];
            if (sum >= 10) {
                result[i] += sum % 10;// 判断是否有进位
                result[i + 1] = 1;
            } else {
                result[i] += sum;
            }
        }

        // 去掉数据最后的0
        StringBuilder stringBuilder = new StringBuilder();
        int start = result.length - 1;
        if (result[result.length - 1] == 0)
            start--;

        //再逆序打出来数组
        for (int i = start; i >= 0; i--) {
            stringBuilder.append(result[i]);
        }
        return stringBuilder.toString();
    }


}
