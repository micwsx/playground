package com.enjoy.hackerrank;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/9/2020 2:43 PM
 */
public class SocksPair {
    public static void main(String[] args) {
        int[] ar = new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20};
        int i = sockMerchant(ar.length, ar);
        System.out.println(i);
    }

    static int sockMerchant(int n, int[] ar) {
        Arrays.sort(ar);
        int count = 0;
        for (int i = 0; i < ar.length - 1; i++) {
            if (ar[i] == ar[i + 1]) {
                count++;
                i++;
            }
        }
        return count;
    }
}
