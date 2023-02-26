package com.enjoy.demo.sort2;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 4/27/2021 8:13 AM
 * @Version 1.0
 */
public class Selection {

    //从最后元素开始，依次与第一个元素比较，找出最小的值。
    //第一轮下来，第一个元素找到，开始第二个元素s，从最后元素开始，依次与第二个元素比较，找出最小值。
    //inductive reasoning...
    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j >= i; j--) {
                if (array[j] < array[i]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 3, 6, 7, 2};
        selectionSort(array);
        System.out.println(Arrays.toString(array));

    }
}
