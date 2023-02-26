package com.enjoy.demo.sort2;

import java.util.Arrays;

/**
 * @author: Michael
 * @date: 2023/2/15 23:17
 * 堆排序（最小堆，最大堆）：先将非叶子结点中找到最大值，放在堆顶。然后将堆顶与最后一个叶子结点交换位置，依次开始比较新元素的大小。
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 5, 7, 8};

        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void heapSort(int[] array) {
        //先从将除叶子结点外的结点中到最大值，放在堆顶。(因为叶子结点没有子元素)
        for (int pos = (array.length / 2) - 1; pos >= 0; pos--) {
            heapAdjust(array, pos, array.length);
        }

        // 每次把第一个元素与最后一个元素交换，再调整树顺序。
        for (int i = array.length - 1; i >= 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heapAdjust(array, 0, i);//每一个循环最后一个就是排好的最大元素
        }

    }

    private static void heapAdjust(int[] array, int pos, int len) {
        int root = array[pos];
        // 从左结点开始
        for (int i = pos * 2 + 1;
             i < len;
             i = i * 2 + 1) { //找到此结点的下一个左结点
            //存在右结点 且左结点<根结点
            if (i + 1 < len && array[i] < array[i + 1]) i++;
            //右结点<根结点
            if (array[i] <= root) break;
            //最大值给root元素
            array[pos] = array[i];
            pos = i;
        }
        array[pos] = root;
    }
}
