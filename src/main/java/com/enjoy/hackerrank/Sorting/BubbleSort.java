package com.enjoy.hackerrank.Sorting;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/10/2020 5:23 PM
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] a = {1, 4, 5, 6, 2};
        countSwaps(a);
    }

    static void countSwaps2(int[] a) {
        int cout = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    cout++;
                    System.out.println(Arrays.toString(a));
                }
            }
        }
        System.out.println("Array is sorted in " + cout + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: 3" + a[a.length - 1]);
    }

    static void countSwaps(int[] a) {
        int cout = 0;
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    cout++;
                    flag = false;
//                    System.out.println(Arrays.toString(a));
                }
            }
        }
        System.out.println(String.format("Array is sorted in %d swaps.%nFirst Element: %d%nLast Element: %d%n",cout,a[0],a[a.length - 1]));
    }


}
