package com.enjoy.demo.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 4/27/2021 8:13 AM
 * @Version 1.0
 */
public class Insertion {

    private static int[] sort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int current = array[i + 1];
            int index = i;
            //
            while (index >= 0 && array[index] > current) {
                array[index + 1] = array[index];
                index--;
            }
            array[index + 1] = current;
        }
        return array;
    }

    public static void main(String[] args) {

        int[] array = {1, 5, 3, 6, 7, 2};
        int[] sort = sort(array);
        System.out.println(Arrays.toString(sort));

    }
}
