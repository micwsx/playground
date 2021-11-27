package com.enjoy.hackerrank.string;

import java.util.Locale;

/**
 * @author Michael
 * @create 12/23/2020 2:47 PM
 */
public class CommonChild {

    public static void main(String[] args) {


        String s3 = timeConversion("07:05:45PM");
        System.out.println(s3);

        String s1 = "TERRACED";
        String s2 = "CRATERED";

        String s = commonChild2(s1, s2);
        System.out.println(s);

//        int i = commonChild(s1, s2);
//        System.out.println(i);
    }

    static String timeConversion(String s) {
        java.time.LocalTime parse = java.time.LocalTime.parse(s, java.time.format.DateTimeFormatter.ofPattern("hh:mm:ssa", Locale.US));
        return parse.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }


    public static int commonChild(String a, String b) {
        int n = a.length();
        int[][] dp = new int[n + 1][n + 1];
        // initialize two-dimension array.
        for (int i = 0, j = 0; i < dp.length; i++) {
            dp[i][j] = 0;
            j++;
            if (j >= dp[i].length) {
                j = 0;
            }
        }
        char[] a_array = a.toCharArray();
        char[] b_array = b.toCharArray();
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (a_array[i - 1] != b_array[j - 1]) {
                    // 取最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp[n][n];
    }

    // Complete the commonChild function below.
    static String commonChild2(String a, String b) {

        int n = a.length();
        String[][] dp = new String[n + 1][n + 1];
        // initialize two-dimension array.
        char[] a_array = a.toCharArray();
        char[] b_array = b.toCharArray();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = "";
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (a_array[i - 1] != b_array[j - 1]) {
                    // 取最大值
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + a_array[i - 1];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println();
        }
        return dp[n][n];

    }


}
