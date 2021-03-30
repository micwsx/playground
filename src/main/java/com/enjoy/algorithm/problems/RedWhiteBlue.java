package com.enjoy.algorithm.problems;

/**
 * @Author wu
 * @Date 3/29/2021 2:51 PM
 * @Version 1.0
 */
public class RedWhiteBlue {

    public static void main(String[] args) {
        int[] arrays = {0, 0, 1, 2, 2, 1, 1, 0, 2, 1, 1, 0, 0};
        // sort
        //计数法
        countSort(arrays);
    }

    private static void countSort(int[] arrays) {

        int min = arrays[0], max = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (min > arrays[i]) {
                min = arrays[i];
            } else if (max < arrays[i]) {
                max = arrays[i];
            }
        }
        int len = max - min + 1;
        int[] result = new int[len];
        for (int k = 0; k < arrays.length; k++) {
            int index = arrays[k] - min;
            result[index]++;
        }

        for (int r = 0; r < result.length; r++) {
            for (int i = 0; i < result[r]; i++) {
                System.out.print(r + ",");
            }
        }
    }


}
