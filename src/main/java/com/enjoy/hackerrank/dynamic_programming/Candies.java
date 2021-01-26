package com.enjoy.hackerrank.dynamic_programming;

import java.util.Arrays;

/**
 * @author Michael
 * @create 1/4/2021 5:26 PM
 * this problem can be solved with a greedy technique.
 */
public class Candies {

    public static void main(String[] args) {
//        int[] ratings=new int[]{4,5,6};
//        int[] ratings = new int[]{6, 5, 4, 7, 6, 9};
        int[] ratings = new int[]{6, 5, 4, 7, 6, 5};
        long minCandies = getMinCandies2(ratings);
        System.out.println(minCandies);

    }

    static long getMinCandies2(int[] ratings) {
        long[] result = new long[ratings.length];
        Arrays.fill(result, 1);
        // 先正向遍历
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                //升序
                result[i] = result[i - 1] + 1;
            }
        }
        // 再反向遍历
        for (int i = ratings.length - 2; i >= 0; i--) {
            //降序且candies数量确小于对方
            if (ratings[i] > ratings[i + 1] && result[i] <= result[i + 1]) {
                result[i] = result[i + 1] + 1;
            }
        }
        return Arrays.stream(result).sum();
    }

    static long getMinCandies(int[] ratings) {
        int[] numberOfRatings = new int[ratings.length];
        long sum = 0;
        Arrays.fill(numberOfRatings, 1);

        for (int i = 1; i < numberOfRatings.length; i++) {
            if (ratings[i] > ratings[i - 1])
                numberOfRatings[i] = numberOfRatings[i - 1] + 1;
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && numberOfRatings[i] <= numberOfRatings[i + 1])
                numberOfRatings[i] = numberOfRatings[i + 1] + 1;
        }

        for (int i = 0; i < numberOfRatings.length; i++) {
            sum += numberOfRatings[i];
        }

        //System.out.println();
        for (int i = 0; i < numberOfRatings.length; i++) {
            System.out.print(numberOfRatings[i] + " ");
        }
        System.out.println();
        return sum;
    }

}
