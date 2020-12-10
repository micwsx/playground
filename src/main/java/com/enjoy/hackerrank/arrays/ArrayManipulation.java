package com.enjoy.hackerrank.arrays;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @author Michael
 * @create 12/10/2020 3:48 PM
 */
public class ArrayManipulation {

    public static void main(String[] args) {
        int[][] queries = new int[][]{
                {1, 2, 100},
                {2, 5, 100},
                {3, 4, 100}
        };
        long l = arrayManipulation(5, queries);
        System.out.println(l);
    }

    static long arrayManipulation(int n, int[][] queries) {
        long[] arr = new long[n];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            int addition = queries[i][2];
            arr[start - 1] += addition;
            if (end < n)
                arr[end] -= addition;
            System.out.println(Arrays.toString(arr));
        }
        // 找最大值
        long cur = arr[0];
        long max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
        }
        return max;
    }


    static long arrayManipulation2(int n, int[][] queries) {
        long[] arr = new long[n];
        long max = 0;
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            int addition = queries[i][2];
            for (int j = start - 1; j < end; j++) {
                arr[j] += addition;
                max = arr[j] > max ? arr[j] : max;
            }
            System.out.println(Arrays.toString(arr));
        }
        return max;
    }


}
