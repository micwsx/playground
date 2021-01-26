package com.enjoy.hackerrank.dynamic_programming;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Michael
 * @create 1/5/2021 12:38 PM
 * 二进制和十进制混合数字
 * Combinatorial Algorithms
 */
public class DecibinaryNumbers {


    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(9000));

        System.out.println(Integer.toString(9000,2));
        System.out.println(Integer.toString(9000,2).length());

//        long d = (long) 1E16;
//        System.out.println(Long.toString(d, 2));
//        System.out.println(Long.toString(d, 2).length());
//
//        System.out.println(Math.pow(2,19));

        int BITS = 19;
        int MAX = 1 << BITS;
        long[][] dp = new long[BITS][MAX];
        for (int i = 0; i < 10; i++)
            dp[0][i] = 1;
        for (int i = 1; i < BITS; i++) {
            int sub = 1 << i;
            for (int j = 0; j < MAX; j++) {
                for (int k = 0; k <= 9; k++) {
                    int rem = j - k * sub;
                    if (rem < 0)
                        break;
                    dp[i][j] += dp[i - 1][rem];
                }
            }
        }
        long sum = 0;
        TreeMap<Long, Integer> sums = new TreeMap<Long, Integer>();
        sums.put(0l, -1);
        for (int i = 0; i < MAX; i++) {
            sum += dp[BITS - 1][i];
            sums.put(sum, i);
        }
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int z = 0; z < q; z++) {
            long n = sc.nextLong();
            int val = sums.floorEntry(n - 1).getValue() + 1;
            n -= sums.floorEntry(n - 1).getKey();
            long ans = 0;
            for (int i = BITS - 1; i > 0; i--) {
                int digit = 0;
                while (dp[i - 1][val] < n) {
                    n -= dp[i - 1][val];
                    digit++;
                    val -= (1 << i);
                }
                ans *= 10;
                ans += digit;
            }
            ans *= 10;
            ans += val;
            System.out.println(ans);
        }
    }
}
