package com.enjoy.hackerrank.hashmap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michael
 * @create 12/24/2020 1:42 PM
 * 是否有公共子串
 */
public class TwoStrings {

    public static void main(String[] args) {
        String s1 = "abbc";
        String s2 = "world";
        String s = twoStrings(s1, s2);
        System.out.println(s);
    }

    static int f(String a) {
        int r = 0;
        for (char c : a.toCharArray()) {
            r |= 1 << c - 'a';
        }
        System.out.println(r);
        return r;
    }

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        int result = s1.chars().reduce(0, (a, b) -> a |= 1 << b - 97);
        System.out.println(result);
        int result2 = s2.chars().reduce(0, (a, b) -> a |= 1 << b - 97);
        return (result & result2) == 0 ? "No" : "Yes";
    }


    // Complete the twoStrings function below.
    static String twoStrings2(String s1, String s2) {
        Set set = new HashSet();
        set.addAll(s1.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        Set set2 = new HashSet();
        set2.addAll(s2.chars().mapToObj(i -> (char) i).collect(Collectors.toList()));

        // 获取次a与b的交集，有则返回true
        boolean b = set.removeAll(set2);

        return b ? "YES" : "No";

    }


}
