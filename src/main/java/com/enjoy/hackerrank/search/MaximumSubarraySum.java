package com.enjoy.hackerrank.search;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.LongStream;

/**
 * @author Michael
 * @create 12/30/2020 1:24 PM
 * 给你一个大小为N的数组和另外一个整数M。你的目标是找到每个子数组的和对M取余数的最大值。 子数组是指原数组的任意连续元素的子集。
 * 注意你需要找到max(子数组的和）%M ， 其中一共有N*(N+1)/2个可能的子数组。
 * Kadane's algorithm.
 * 求子数组元素总和对M最大余数值。
 */
public class MaximumSubarraySum {

    public static void main(String[] args) {


        System.out.println(1 % 5);
        System.out.println(-2 % 5);
        System.out.println(-1 % 5);

//        String s = "846930887 1681692778 1714636916 1957747794 424238336 719885387 1649760493 596516650 1189641422 1025202363 1350490028 783368691 1102520060 2044897764 1967513927 1365180541 1540383427 304089173 1303455737 35005212 521595369 294702568 1726956430 336465783 861021531 278722863 233665124 2145174068 468703136 1101513930 1801979803 1315634023 635723059 1369133070 1125898168 1059961394 2089018457 628175012 1656478043 1131176230 1653377374 859484422 1914544920 608413785 756898538 1734575199 1973594325 149798316 2038664371 1129566414";
//        long[] array = Arrays.stream(s.split(" ")).mapToLong(v -> Long.parseLong(v)).toArray();
//        long m = 1804289384;
        //long[] array = new long[]{3, 3, 9, 9, 5};
        //long m = 7;
//        long[] array = new long[]{1, 1, 1, 2, 1};
//        long[] array = new long[]{6, 6};
        long[] array = new long[]{1, -2};
        long m = 5;
//        solve(array, m);
        long result = maximumSum(array, m);
        System.out.println(result);
    }

    public static void solve(long[] array, long modulo) {
        int elementsCount = array.length;
        long result = 0;
        long currentPrefixSum = 0;
        TreeSet<Long> prefixSums = new TreeSet<>();
        prefixSums.add(0L);
        for (int i = 0; i < elementsCount; i++) {
            long currentElement = array[i] % modulo;
            currentPrefixSum = (currentPrefixSum + currentElement) % modulo;
            prefixSums.add(currentPrefixSum);
            result = Math.max(result, currentPrefixSum - prefixSums.first());
            Long firstLargerSum = prefixSums.higher(currentPrefixSum);
            if (firstLargerSum != null) {
                result = Math.max(result, currentPrefixSum - firstLargerSum + modulo);
            }
        }
        System.out.println(result);
    }

    /**
     * 求最大余数的子数组总和
     *
     * @param a
     * @param m
     * @return
     */
    static long maximumSum(long[] a, long m) {
        long sum_prefix = 0, max_sub_sum = 0;
        TreeSet<Long> set = new TreeSet<>();
        set.add(0L);
        for (long item : a) {
            //  long currentElement = item % m;
            // 当前元素+之前prefix求余值得出此元素包含之前所有元素的求余结果。
            sum_prefix = (item + sum_prefix) % m;
            set.add(sum_prefix);
            // 比较得出最大的余数结果。
            max_sub_sum = Math.max(max_sub_sum, sum_prefix - set.first());
            Long higher = set.higher(sum_prefix);
            if (higher != null) {
                // 比如数组：1 1 1 2 [M=5]
                max_sub_sum = Math.max(max_sub_sum, sum_prefix - higher + m);
            }
        }
        return max_sub_sum;
    }

    /**
     * 求子数组和最大值。
     * 支持负数元素数组
     *
     * @param a
     * @return
     */
    static long maximumSubarraySum(long[] a) {
        long max_current = a[0], max_global = a[0];
        for (int i = 1; i < a.length; i++) {
            max_current = Math.max(a[i], max_current + a[i]);
            max_global = Math.max(max_current, max_global);
        }
        // 最大和
        return max_global;
    }


}
