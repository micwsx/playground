package com.enjoy.algorithm.sort;

/**
 * @Author wu
 * @Date 3/25/2021 2:09 PM
 * @Version 1.0
 * 选择排序:首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * 找到最小值的索引，然后跟顺序中对应位置互换值。位置0的找到后面数组中最小的索引跟0这个位置值互换，再开始1位置，找后面最小的跟1位置互换。
 *
 */
public class SelectionSort {

    public int[] sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int min_index = i;//假设第一个位置是最小的
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min_index]) {
                    //获取最小值的索引
                    min_index=j;
                }
            }
            int temp=array[i];
            array[i]=array[min_index];
            array[min_index]=temp;
        }
        return array;
    }


}
