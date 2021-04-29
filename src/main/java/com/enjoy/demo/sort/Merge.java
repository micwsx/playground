package com.enjoy.demo.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 4/27/2021 8:13 AM
 * @Version 1.0
 */
public class Merge {

    private static int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length >> 1;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merger2Array(sort(left), sort(right));
    }

    private static int[] merger2Array(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        for (int i = 0, m = 0, n = 0; i < result.length; i++) {
            if (m >= a.length) {
                //a元素获取结束
                result[i] = b[n++];
            } else if (n >= b.length) {
                //b元素获取结束
                result[i] = a[m++];
            } else if (a[m] > b[n]) {
                // a中元素大于b中元素
                result[i] = b[n++];
            } else {
                // a中元素小于b中元素
                result[i] = a[m++];
            }
        }
        return result;
    }


    public static void main(String[] args) {

        int[] array = {1, 5, 3, 6, 7, 2};
        int[] sort = sort(array);
        System.out.println(Arrays.toString(sort));

    }
}
