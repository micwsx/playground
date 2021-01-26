package com.enjoy.hackerrank.dynamic_programming;

/**
 * @author Michael
 * @create 12/31/2020 3:26 PM
 * 非邻近元素组成的子数组总和最大值
 */
public class MaxArraySum {

    public static void main(String[] args) {
//        int[] arr = new int[]{2, 1, 5, 8, 4};
//        int[] arr = new int[]{3, 5, -7, 8, 10};
        int[] arr = new int[]{3, 5, -7, 8, 10};

        int i = maxSubsetSum(arr);
        System.out.println(i);
    }

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum2(int[] arr) {
        if (arr.length == 0) return 0;
        arr[0] = Math.max(0, arr[0]);
        if (arr.length >= 2)
            arr[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            arr[i] = Math.max(arr[i - 1], arr[i] + arr[i - 2]);
        }
        return arr[arr.length - 1];
    }

    public static int maxSubsetSum(int[] arr) {
        if (arr.length == 0) return 0;
        arr[0] = Math.max(0, arr[0]);
        if (arr.length == 1) return arr[0];
        arr[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++)
            arr[i] = Math.max(arr[i - 1], arr[i] + arr[i - 2]);
        return arr[arr.length - 1];
    }
}
