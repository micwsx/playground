package com.enjoy.hackerrank.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Michael
 * @create 12/25/2020 12:07 PM
 * 等比数列
 */
public class CountTriplets {

    public static void main(String[] args) {

//        List<Long> arr = new ArrayList<Long>(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L));
        List<Long> arr = new ArrayList<Long>(Arrays.asList(1L, 5L, 5L, 25L, 125L));
        long r = 5;
        long l = countTriplets(arr, r);
        System.out.println(l);
    }

    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long, Long> rightMap = new HashMap<>();
        // 初始化rightMap记录每个数值出现次数
        for (Long num : arr) {
            if (rightMap.containsKey(num)) {
                Long count = rightMap.get(num);
                rightMap.put(num, ++count);
            } else {
                rightMap.put(num, 1L);
            }
        }

        HashMap<Long, Long> leftMap = new HashMap<>();
        long totalCount = 0;
        for (Long num : arr) {

            long left_count = 0;
            long right_count = 0;
            // 左边数值。判断数值是否是r的倍数，是则获取整除值，否则为0
            long left_value = num % r == 0 ? num / r : 0;
            // 右边数值
            long right_value = num * r;

            // 当前数值出现次数减1
            Long count = rightMap.get(num);
            count--;
            rightMap.put(num, count);

            // 获取右边期望的值出现次数
            if (rightMap.containsKey(right_value)) {
                right_count = rightMap.get(right_value);
            }
            // 获取左边期望的值出现次数
            if (leftMap.containsKey(left_value)) {
                left_count = leftMap.get(left_value);
            }

            totalCount += left_count * right_count;
            System.out.println(left_value + "[" + left_count + "次]," + num + "," + right_value + "[" + right_count + "次]");
            //将当前数添加到leftMap,记录次数
            if (leftMap.containsKey(num)) {
                Long aLong = leftMap.get(num);
                leftMap.put(num, ++aLong);
            } else {
                leftMap.put(num, 1L);
            }
        }
        return totalCount;
    }


    // logx(y)=loge(y)/loge(x);
    static int log(long value, long base) {
        return (int) (Math.log(value) / Math.log(base));
    }

    // i<j<k
    // Complete the countTriplets function below.
    static long countTriplets2(List<Long> arr, long r) {
        int size = arr.size();
        long[][] ratio = new long[size][1];
        for (int i = 0; i < arr.size(); i++) {
            Long num = arr.get(i);
            int value = log(num, r);
            ratio[i][0] = value;
        }

//        for (int i = 0; i < ratio.length; i++) {
//            System.out.println(ratio[i][0] + ",");
//        }

        long count = 0;
        // 3个数拿出来是否成等比(geometric progression)数列
        for (int i = 0; i < ratio.length; i++) {
            long first = ratio[i][0];
            for (int j = i + 1; j < ratio.length; j++) {
                long second = ratio[j][0];
                long subtract = second - first;
                if (subtract == 1) {
                    for (int k = j + 1; k < ratio.length; k++) {
                        long third = ratio[k][0];
                        long di = third - second;
                        if (di == subtract) {
                            count++;
                            // System.out.println(i + "," + j + "," + k);
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return count;
    }
}
