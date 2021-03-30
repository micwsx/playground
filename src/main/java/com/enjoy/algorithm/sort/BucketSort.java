package com.enjoy.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author wu
 * @Date 3/27/2021 3:52 PM
 * @Version 1.0
 * 局限：必须是整数且有一定范围
 */
public class BucketSort {

    public static void main(String[] args) {
        BucketSort countingSort = new BucketSort();
        double[] numbers = {4.5, 0.48, 3.25, 2.18, 0.5};

        double[] sort = countingSort.sort(numbers);
        System.out.println(Arrays.toString(sort));
    }

    public double[] sort(double[] array) {
        // 1.得到最大值和最小值，计算差值d
        //先找到最大值和最小值
        double min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        double d = max - min; // 4，

        //2.初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(i, new LinkedList<Double>());
        }
        //3.遍历原始数组，将每个元素放入桶中。
        // 区度跨域 (max-min)/(桶数量-1)=1。
        // 5个桶的范围是
        // [0.5,1.5)
        // [1.5,2.5)
        // [2.5,3.5)
        // [3.5,4.5)
        // [4.5,4.5]
        for (int i = 0; i < array.length; i++) {
            int seqNum = (int) ((array[i] - min) * (bucketNum - 1) / (max - min));
            bucketList.get(seqNum).add(array[i]);
        }

        //4.对每个桶内部排序
        for (int i = 0; i < bucketList.size(); i++) {
            // 内部使用归并排序或Tim排序
            Collections.sort(bucketList.get(i));
        }

        //5.输出全部元素
        double[] result = new double[array.length];
        int k=0;
        for (int i = 0; i < bucketList.size(); i++) {
            LinkedList<Double> bucket = bucketList.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                Double element = bucket.get(j);
                result[k++]=element;
            }
        }
        return result;
    }
}