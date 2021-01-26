package com.enjoy.hackerrank.GreedyAlgorithms;

import java.util.Arrays;

/**
 * @author Michael
 * @create 1/19/2021 4:40 PM
 * 找一数组中两元素差绝对值最小值.
 */
public class MinimumAbsoluteDifference {

    public static void main(String[] args) {

        int[] arr = {};
        int i = minimumAbsoluteDifference(arr);
        System.out.println(i);
    }

    // Complete the minimumAbsoluteDifference function below.
    static int minimumAbsoluteDifference(int[] arr) {
        Arrays.sort(arr);
        int min = 0;
        if (arr.length >= 2) {
            min = Math.abs(arr[0] - arr[1]);
        }
        for (int i = 1; i < arr.length - 1; i++) {
            int result = Math.abs(arr[i] - arr[i + 1]);
            min = Math.min(result, min);
        }
        return min;
    }

}
