package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/25/2021 4:18 PM
 * @Version 1.0
 * 快速排序：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 1．先从数列中取出一个数作为基准数。
 * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 * 3．再对左右区间重复第二步，直到各区间只有一个数。
 * <p>
 * 最佳情况：T(n) = O(nlogn)
 * 最差情况：T(n) = O(n2)
 * 平均情况：T(n) = O(nlogn)
 * 递归的把数组分区，使pivot值中两边中间。最终保证数组有序。
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
//        int[] sort = quickSort.sort(new int[]{1, 2, 3, 4});
//        int[] arrays = new int[]{4, 2, 3, 5, 6, 9, 3, 7};
        int[] arrays = new int[]{5, 4, 2, 3};

        int[] sort = quickSort.sort(arrays);

        System.out.println(Arrays.toString(sort));
    }

    public int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }
    
    /**
     * @param array
     * @param start 索引下标
     * @param end   索引下标
     * @return
     */
    public int[] sort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
//        int pivot = partitionWithLowAndHigh(array, start, end);
        int pivot = partition(array, start, end);
        if (start < pivot)
            sort(array, start, pivot - 1);
        if (pivot < end)
            sort(array, pivot + 1, end);
        return array;
    }


    private int partition(int[] array, int startIndex, int endIndex) {
//        int randomOne = (start + end) >> 1;
//        // 将小于数放pivot左边，大于数放pivot右边
//        // 将中间与第一个元素交换位置。
//        swap(array, randomOne, start);
        //以第一个为Pivot
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            // 如果索引下的值比最后一个元素小
            if (array[i] < pivot) {
                mark++;
                swap(array, i, mark);
            }
        }
        swap(array, startIndex, mark);
        return mark;
    }

    /**
     * 双边循环方法partition
     */
    private int partitionWithLowAndHigh(int[] arrays, int startIndex, int endIndex) {
        // 以第一个数值作为pivot
        // pivot
        int pivot = arrays[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            //先从right开始查找比pivot小的数为止
            while (right > left && arrays[right] > pivot) right--;
            //先从left开始查找比pivot大的数为止
            while (left < right && arrays[left] <= pivot) left++;
            if (left < right)
                swap(arrays, left, right);
        }
        // 最后将left/right 位置与pivot交换
        swap(arrays, startIndex, left);
        return left;
    }


        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

}
