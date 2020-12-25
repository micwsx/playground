package com.enjoy.hackerrank.Sorting;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/10/2020 5:48 PM
 * 背包问题
 */
public class MarkAndToys {

    //https://www.cnblogs.com/banyu/p/4994999.html
    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 4, 3, 2};
//        int[] arr = new int[]{ 4, 3, 2};

        long l = countInversions(arr);
        System.out.println(l);
        System.out.println(Arrays.toString(arr));

//        int money = 50;
//        int[] pricesOfGift = new int[]{1, 12, 5, 111, 200, 1000, 10};
//        int maxNum = maximumToys(pricesOfGift, money);
//        System.out.println(maxNum);
    }

    // 将数组相信数值排序，计算相信交换次数
    // Complete the countInversions function below.
    static long countInversions(int[] arr) {

        if (arr.length <= 1)
            return 0;
        int mid = arr.length / 2;//1
        int[] leftArray = Arrays.copyOfRange(arr, 0, mid);//4
        int[] rightArray = Arrays.copyOfRange(arr, mid, arr.length);//3, 2

        long count = 0;
        count += countInversions(leftArray);
        count += countInversions(rightArray);

        int left = 0;
        int right = 0;
        int range=arr.length-mid;
        // 两个长度不等有序的数组进行合并。
        for (int i = 0; i < arr.length; i++) {
            if (left < mid && (right >= range || leftArray[left] <= rightArray[right])) {
                arr[i] = leftArray[left++];
                count += right;
            } else {
                arr[i] = rightArray[right++];
            }
            System.out.println(Arrays.toString(arr));
        }
        return count;
    }





    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        // 先不过滤
        int num = prices.length;
        int[][] matrix = new int[num][num];//二维数组表
        for (int i = 0; i < num; i++) { // 商户数量
            for (int j = 0; j < num; j++) {// 商品价格
                int price = prices[j];
                matrix[i][j] = price;

            }
        }
        return 0;
    }

}
