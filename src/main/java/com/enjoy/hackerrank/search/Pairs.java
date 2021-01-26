package com.enjoy.hackerrank.search;

import java.util.HashMap;

/**
 * @author Michael
 * @create 12/29/2020 4:27 PM
 */
public class Pairs {

    public static void main(String[] args) {

        int k = 2;
        int[] array = {1, 5, 3, 4, 2};
        int pairs = pairs(k, array);
        System.out.println(pairs);
    }

    /**
     * @param k   2
     * @param arr 1 5 3 4 2
     * @return 3 - There are 3 pairs of integers in the set with a difference of 2: [5,3], [4,2] and [3,1] .
     */
    static int pairs(int k, int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.putIfAbsent(i, 1);
        }
        int result = 0;
        for (int i : arr) {
            int target = i + k;
            if (map.remove(target) != null)
                result++;
        }
        return result;
    }

}
