package com.enjoy.hackerrank.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Michael
 * @create 12/21/2020 12:45 PM
 */
public class MakingAnagrams {

    public static void main(String[] args) {

        String a = "cde";
        String b = "abc";

        int i = makeAnagram(a, b);
        System.out.println(i);

    }

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {

        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();

        int[] fre = new int[26];
        a.chars().forEach(i -> fre[i - 97]++);
        b.chars().forEach(i -> fre[i - 97]--);

        int sum = java.util.stream.IntStream.of(fre).map(Math::abs).sum();
//        int sum1 = Arrays.stream(fre).map(Math::abs).sum();
        System.out.println(Arrays.toString(IntStream.of(fre).filter(i -> i > 0).toArray()));
        System.out.println(Arrays.toString(IntStream.of(fre).filter(i -> i < 0).toArray()));
        return sum;
    }

}
