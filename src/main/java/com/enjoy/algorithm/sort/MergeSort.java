package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/25/2021 3:50 PM
 * @Version 1.0
 * 归并排序：该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 递归将数组拆分最小的两个数组排序，再合并两个有顺的数组。
 * O(nlogn)
 *     步骤1：把长度为n的输入序列分成两个长度为n/2的子序列；
 *     步骤2：对这两个子序列分别采用归并排序；
 *     步骤3：将两个排序好的子序列合并成一个最终的排序序列。
 */
public class MergeSort {


    public int[] sort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        // 将数组left和right再划分更小的数组,再合并
        int[] result = merge(sort(left), sort(right));
        return result;
    }

    // 合并两个排序好的数组
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] < right[j]) {
                result[index] = left[i++];
            } else {
                result[index] = right[j++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] sort = mergeSort.sort(new int[]{1, 3, 2, 6, 7, 9, 4, 6});
        System.out.println(Arrays.toString(sort));
    }


}
