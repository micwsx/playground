package com.enjoy.hackerrank.Sorting;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/10/2020 5:23 PM
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] a = {1, 4, 5, 6, 2};
        bubbleSort(a);
//        bubbleSort3(a);
        System.out.println(Arrays.toString(a));
//        countSwaps(a);
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
        System.out.println(String.format("Array is sorted in %d swaps.%nFirst Element: %d%nLast Element: %d%n", cout, a[0], a[a.length - 1]));
    }

    // int[] a = { 1,4, 5, 6, 2};
    static void bubbleSort(int[] array) {
        if (array == null) return;
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = array.length - 1; j >= i; j--) {
                if (array[j] < array[i]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    System.out.println(Arrays.toString(array));
                }
            }
        }
    }

    public static void bubbleSort3(int[] arr) {
        if (arr == null) {
            return;
        }
        boolean flag;
        for (int i = arr.length - 1; i > 0; i--) {
            flag = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }


}
