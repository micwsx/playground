package com.enjoy.demo.sort2;

import java.util.Arrays;

/**
 * @author: Michael
 * @date: 2023/2/13 22:47
 * 冒泡排序是相邻元素，两两比对交换位置，每一趟比对后，把最在元素排在最后，总共要走n-1趟。
 */
public class Bubble {

    public static void main(String[] args) {
//        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        int[] array = new int[]{4, 2, 1, 5, 9, 6};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = 1; j <= array.length - 1 - i; j++) {
                if (array[j] < array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }


    /**
     *
     *
     * @param array
     */
    private static void insertionSort2(int[] array) {
        if (array == null) return;
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            for (int j = array.length - 1; j <= i; j--) {
                if (array[j] < array[i]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }

}
