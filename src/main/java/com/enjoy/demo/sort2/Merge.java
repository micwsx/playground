package com.enjoy.demo.sort2;

import java.util.Arrays;

/**
 * @author: Michael
 * @date: 2023/2/15 23:17
 * 合并排序：分治思想，递归方式实现。将拆分最小的两个数组，最后将两有序数组合并。
 */
public class Merge {


    public static void main(String[] args) {
        int[] array = new int[]{1, 4, 3, 6, 5};
        int[] result = mergeSort(array);
        System.out.println(Arrays.toString(result));
    }

    private static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length >> 2;
        int[] left = Arrays.copyOfRange(array, 0, mid + 1);
        int[] right = Arrays.copyOfRange(array, mid + 1, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] a, int[] b) {
        int aLength = a.length;
        int bLength = b.length;
        int[] sortedArray = new int[aLength + bLength];
        int i = 0, j = 0;
        for (int k = 0; k < sortedArray.length; k++) {
            if (i >= aLength) {
                sortedArray[k] = b[j++];
            } else if (j >= bLength) {
                sortedArray[k] = a[i++];
            } else if (a[i] > b[j]) {
                sortedArray[k] = b[j++];
            } else {
                sortedArray[k] = a[i++];
            }
        }
        return sortedArray;
    }

}
