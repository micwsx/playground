package com.enjoy.demo.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 4/27/2021 8:13 AM
 * @Version 1.0
 */
public class Bubble {

    private static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // 交换位置
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {

        int[] array = {1, 5, 3, 6, 7, 2};
        int[] sort = sort(array);
        System.out.println(Arrays.toString(sort));

    }
}
