package com.enjoy.hackerrank.search;

import java.util.*;

/**
 * @author Michael
 * @create 12/29/2020 4:48 PM
 */
public class TripleSum {

    public static void main(String[] args) {

        int a[] = new int[]{1, 3, 5};
        int b[] = new int[]{2, 3};
        int c[] = new int[]{1, 2, 3};

        long triplets = triplets(a, b, c);
        System.out.println(triplets);

    }

    /**
     * 三个数组 a,b,c; p属于a,q属于b,r属于c,满足p<= q && q>=r,共有多少个这个的组合
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    static long triplets(int[] a, int[] b, int[] c) {

        int[] a_array = Arrays.stream(a).sorted().distinct().toArray();
        int[] b_array = Arrays.stream(b).sorted().distinct().toArray();
        int[] c_array = Arrays.stream(c).sorted().distinct().toArray();

        long result = 0;
        int x, y, z;
        x = y = z = 0;
        while (y < b_array.length) {
            while (x < a_array.length && a_array[x] <= b_array[y])
                x++;
            while (z < c_array.length && c_array[z] <= b_array[y])
                z++;
            result += (long) x * z;
            y++;
            x = z = 0;
        }
        return result;
    }

}
