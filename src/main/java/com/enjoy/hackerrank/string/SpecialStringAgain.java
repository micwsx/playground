package com.enjoy.hackerrank.string;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Michael
 * @create 12/21/2020 5:40 PM
 */
public class SpecialStringAgain {

    public static void main(String[] args) {
        String s = "abcbaba";
        long l = substrCount(s.length(), s);
        System.out.println(l);
    }


    static long substrCount(int n, String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        // 获取字符串的所有子串
        for (int i = 0; i < chars.length; i++) {
            char startChar = chars[i];
            int diffIndex = 0;
            for (int j = i + 1; j < chars.length; j++) {
                int current = chars[j];
                if (current == startChar) {
                    if (diffIndex == 0 || (diffIndex - i) == (j - diffIndex)) {
                        char[] result = Arrays.copyOfRange(chars, i, j+1);
                        //System.out.println(String.valueOf(result));
                        count++;
                    }
                }else {
                    if (diffIndex == 0) {
                        diffIndex = j;
                    } else {
                        break;
                    }
                }
            }
        }
        return s.length()+count;
    }


    // 计算子串满足条件(全都一样或者对称字符串)的数量
    // Complete the substrCount function below.
    static long substrCount2(int n, String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        // 获取字符串的所有子串
        for (int len = 1; len <= chars.length; len++) {
            for (int j = 0; j < chars.length; j++) {
                int temp = j + len;
                if (chars.length - j >= len) {
                    String substring = s.substring(j, temp);
                    if (temp == 1 || meetCondition(substring)) {
                        count++;
                        //System.out.println(substring);
                    }
                }
            }
        }
        return count;
    }

    public static boolean meetCondition(String s) {
        // 所有字母都一样
        char[] chars = s.toCharArray();
        int mid = s.length() / 2;
        for (int i = 0; i < mid; i++) {
            if (!(chars[i] == chars[s.length() - i - 1] && chars[i] == chars[0])) {
                return false;
            }
        }
        return true;
    }

}
