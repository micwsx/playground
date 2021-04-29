package com.enjoy.demo.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 4/27/2021 8:13 AM
 * @Version 1.0
 */
public class Selection {

    private static int[] sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                // 获取最小值
                if (min > array[j]) {
                    // 交换位置
                    min = array[j];
                    array[j] = array[i];
                    array[i] = min;
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
