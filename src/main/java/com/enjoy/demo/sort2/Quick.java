package com.enjoy.demo.sort2;

import java.util.Arrays;

/**
 * @author: Michael
 * @date: 2023/2/14 21:45
 * 快速排序：分治方法，递归实现排序。以某个pivot标准基数，将元素大于pivot放在右边，小于pivot元素放在左边。递归排序后得到结果。
 */
public class Quick {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 4, 7};
//        quicksort(array, 0, array.length - 1);
        quickSort2(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void quicksort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIndex = quickSortHelper(arr, left, right);
        quicksort(arr, 0, pivotIndex);
        quicksort(arr, pivotIndex + 1, right);
    }


    private static void quickSort2(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIndex = partition(arr, left, right);
        quickSort2(arr, 0, pivotIndex);
        quickSort2(arr, pivotIndex + 1, right);
    }

    /**
     * 单边循环法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int mark = left;
        for (int i = mark; i <= right;i++) {
            if (arr[i] < pivot) {
                mark++;
                int tmp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = tmp;
            }
        }
        arr[left] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    /**
     * 双边循环法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int quickSortHelper(int[] arr, int left, int right) {
        int tmp = arr[left];
        int i = left;
        int j = right;
        while (i != j) {
            while (i < j && arr[j] >= tmp) {
                j--;
            }
            while (i < j && arr[i] <= tmp) {
                i++;
            }
            if (i < j) {
                int tmp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp1;
            }
        }
        arr[left] = arr[j];
        arr[j] = tmp;
        return i;
    }
}
