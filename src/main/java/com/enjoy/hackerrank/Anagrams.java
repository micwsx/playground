package com.enjoy.hackerrank;

import java.util.Arrays;

/**
 * @author Michael
 * @create 12/9/2020 11:55 AM
 * The two strings contains all the same letters in the same frequencies, so we print "Anagrams". for example, CAT ACT and TCA etc.
 */
public class Anagrams {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        char[] a_array = a.toUpperCase().toCharArray();
        char[] b_array = b.toUpperCase().toCharArray();
        java.util.Arrays.sort(a_array);
        java.util.Arrays.sort(b_array);
        return java.util.Arrays.equals(a_array,b_array);
    }

    public static void main(String[] args) {
        //思路： 统计a和b每个字母出现的次数是否一样？一样则是anagram.
        // 直接转换成大写字母，排序再比较。
        String a = "xyzw";
        String b = "xyxy";

        if (isAnagram(a, b)) {
            System.out.println(a);
            System.out.println(b);
        } else {
            System.out.println("not anagrams");
        }
    }
}
