package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/25/2021 1:25 PM
 * @Version 1.0
 * 冒泡算法 时间复杂度O(n2)
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 */


public class BubbleSort  {
    public int[] sort(int[] array) {
        return sort(array, false);
    }

    public int[] sort(int[] array, boolean desc) {
        boolean exchange = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (desc) {
                    // 后面数字小于前面数值交互位置，把最小的的冒在最后面。
                    if (array[j] < array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        exchange = true;
                    }
                } else {
                    // 后面数字大于前面数值交互位置，把最大的的冒在最后面。
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        exchange = true;
                    }
                }
            }
            if (!exchange) {
                break;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 8, 3, 2, 6, 9};
        BubbleSort sort = new BubbleSort();
        int[] result = sort.sort(array);
        System.out.println("Bubble sort: "+Arrays.toString(result));
        SelectionSort selectionSort = new SelectionSort();
        result = selectionSort.sort(array);
        System.out.println("Selection sort: "+Arrays.toString(result));

    }
}
