package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/25/2021 2:42 PM
 * @Version 1.0
 * 插入排序：O(n2)
 * 步骤1: 从第一个元素开始，该元素可以认为已经被排序；
 * 步骤2: 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 步骤3: 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 步骤4: 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 步骤5: 将新元素插入到该位置后；
 * 步骤6: 重复步骤2~5。
 */
public class InsertionSort  {


    public int[] sort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int current = array[i + 1];// 保存这个值
            int index = i;

            while (index >= 0 && array[index] > current) {
                array[index + 1] = array[index];
                index--;
            }
            // 最后找到合适插入的位置直接将什插入这个地方
            array[index + 1] = current;
        }
        return array;
    }

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        int[] sort1 = sort.sort(new int[]{1, 4, 2,6,8,5});
        System.out.println(Arrays.toString(sort1));
    }

}