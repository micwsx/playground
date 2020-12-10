package com.enjoy.hackerrank.arrays;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/10/2020 2:14 PM
 */
public class MinimumSwap {
    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 2};
        int i = minimumSwaps3(arr);
        System.out.println(i);
    }

    /**
     * 支持不连续的数字，但如果数字集合太大，会有时间消耗问题。
     * @param arr
     * @return
     */
    static int minimumSwaps(int[] arr) {
        int count=0;
        for (int j = 0; j < arr.length; j++) {
            int min = arr[j];
            int index = j;// 最小值索引
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                    index = i;
                }
            }

//            System.out.println("min:" + min);
            if (arr[j] > min) {
                int temp = arr[j];
                arr[j] = min;
                arr[index] = temp;
                count++;
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println(count);
        return count;
    }

    /**
     * 因为是连续的数字，所以可以这样实现。
     * @param a
     * @return
     */
    static int minimumSwaps3(int[] a) {
        int swap=0;
        for(int i=0;i<a.length;i++){
            if(i+1!=a[i]){
                int t=i;
                while(a[t]!=i+1){
                    t++;
                }
                int temp=a[t];
                a[t]=a[i];
                a[i]=temp;
                swap++;
            }
        }
        return swap;

    }

}
