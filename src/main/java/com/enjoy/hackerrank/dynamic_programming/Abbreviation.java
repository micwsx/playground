package com.enjoy.hackerrank.dynamic_programming;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michael
 * @create 1/2/2021 10:12
 * make strng a equal to string b. Otherwise, print NO.
 * 1.Capitalize zero or more of a's lowercase letters.(可以将小写转大写不能将大写转小写)
 * 2.Delete all of remaining lowercase letters in a.(只能删除小写不能删除大写)
 * e.g. daBcd -> ABC
 * daBcd ->dABCd->ABC
 * 建议画matrix发现规律
 * 相关视频：https://www.youtube.com/watch?v=NnD96abizww
 */
public class Abbreviation {

    public static void main(String[] args) {
//        String a = "AfPZN";
//        String b = "APZNC";
//        String a = "Pi";
//        String b = "P";
//        String a = "beFgH";
//        String b = "EFH";

//        String a = "daBcd";
//        String b = "ABC";
        String a = "AbCdE";
        String b = "AFE";
        String abbreviation = abbreviation(a, b);
        System.out.println(abbreviation);
    }


    public static String abbreviation2(String a, String b) {
        boolean[][] mem = new boolean[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) mem[i][j] = true;
                else {
                    if (a.charAt(i - 1) >= 'A' && a.charAt(i - 1) <= 'Z') {
                        mem[i][j] = a.charAt(i - 1) == b.charAt(j - 1) && mem[i - 1][j - 1];
                    } else {
                        mem[i][j] = mem[i - 1][j];
                        if (a.charAt(i - 1) + 'A' - 'a' == b.charAt(j - 1)) {
                            mem[i][j] = mem[i][j] || mem[i - 1][j - 1];
                        }
                    }
                }
            }
        }
        return mem[a.length()][b.length()] ? "YES" : "NO";
    }


    /**
     * @param aString: the string to modify.English letters, ascii[A-Z].
     * @param bString: the string to match.English letters, ascii[A-Z].
     * @return
     */
    static String abbreviation(String aString, String bString) {
        char[] a = aString.toCharArray();
        char[] b = bString.toCharArray();
        boolean[][] dp = new boolean[a.length + 1][b.length + 1];
        for (int i = 0; i <= a.length; i++)
            dp[i][0] = true;

        for (int i = 1; i <= a.length; i++) {
            if (a[i - 1] >= 'A' && a[i - 1] <= 'Z') {
                //大写字符
                for (int j = 1; j <= b.length; j++) {
                    // 比较b中每个字符
                    if (b[j - 1] == a[i - 1])
                        dp[i][j] = dp[i - 1][j - 1];//相等，
                }
            } else {
                //小写字符
                char c = (char) (a[i - 1] - 32);
                for (int j = 1; j <= b.length; j++) {
                    // 比较b中每个字符
                    if (b[j - 1] == c)
                        dp[i][j] = dp[i - 1][j - 1];
                    dp[i][j] |= dp[i - 1][j];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }


        return dp[a.length][b.length] ? "YES" : "NO";
    }

}
