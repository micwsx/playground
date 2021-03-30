package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/27/2021 3:05 PM
 * @Version 1.0
 * 计算排序：核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是《有确定范围的整数》。
 * 步骤1：找出待排序的数组中最大和最小的元素；
 * 步骤2：统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 步骤3：对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 步骤4：反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 */
public class SimpleCountingSort  {

    public static void main(String[] args) {
        SimpleCountingSort countingSort = new SimpleCountingSort();
        // 假如数组值范围是在[1,10]
        int[] numbers = {1, 1, 5, 6, 6, 6, 10, 10, 3, 3, 3, 2, 2, 4, 0, 4, 4, 5, 5};
        int[] sort = countingSort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }


    public int[] sort(int[] array) {
        //先找到最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }

        int[] countArray = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            int index = array[i];
            countArray[index]++;
        }

        int[] result = new int[array.length];
        int m = 0;
        for (int k = 0; k < countArray.length; k++) {
            int count = countArray[k];
            while (count > 0) {
                result[m++] = k;
                count--;
            }
        }
        return result;
    }
}
