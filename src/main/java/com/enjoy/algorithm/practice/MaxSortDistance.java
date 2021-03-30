package com.enjoy.algorithm.practice;

/**
 * @Author wu
 * @Date 3/27/2021 8:16 PM
 * @Version 1.0
 * 计算一个无序数组，相邻最大值。
 */
public class MaxSortDistance {

    public static void main(String[] args) {
        int[] array = {2, 6, 3, 4, 5, 10, 9};
        int maxSortedDistance = getMaxSortedDistance(array);
        System.out.println(maxSortedDistance);
    }

    //利用计数排序法
    public static int getMaxSortedDistance(int[] array) {
        // 获取最大值和最小值
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (min > array[i])
                min = array[i];
        }

        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            int countIndex = array[i] - min;
            countArray[countIndex]++;
        }

        // 计算中间0相差最多的值。
        int result = 1;
        for (int i = 1; i < countArray.length; i++) {
            if (countArray[i] == 0)
                result++;
        }
        return result;
    }



}
