package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/27/2021 3:52 PM
 * @Version 1.0
 * 局限：必须是整数且有一定范围
 */
public class CountingSort {

    public static void main(String[] args) {

        int[] aaaa = {1, 2};
        int a = 0;
        System.out.println(aaaa[++a]);// 2
        System.out.println(aaaa[a--]);// 2
        System.out.println(a);//0

        CountingSort countingSort = new CountingSort();
        // 假如数组值范围是在[1,10]
        int[] numbers = {98, 95, 93, 94, 96, 98, 98};
        int[] sort = countingSort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }

    public int[] sort(int[] array) {
        //先找到最大值98和最小值93
        int min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }

        int[] countingArray = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            int index = array[i] - min;//得出相对地址下标索引
            countingArray[index]++;
        }

        // 然后将数组相加
        for (int i = 1; i < countingArray.length; i++) {
            countingArray[i] += countingArray[i - 1];
        }

        // 最后
        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int countIndex = array[i] - min;
            result[--countingArray[countIndex]] = array[i];
        }

        return result;
    }
}