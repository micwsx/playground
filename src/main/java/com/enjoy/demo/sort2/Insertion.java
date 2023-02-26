package com.enjoy.demo.sort2;

import java.util.Arrays;

/**
 * @author: Michael
 * @date: 2023/2/14 17:34
 * 插入排序：假设第一元素已经是有序，接下来的元素从有序的集合中一个个比较，插入
 */
public class Insertion {

    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 5, 4, 7};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void insertionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0 && j <= i + 1; j--) {
                if (array[j] < array[j - 1]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }




}
