package com.enjoy.hackerrank.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Michael
 * @create 12/24/2020 5:06 PM
 */
public class Sherlock {

    public static void main(String[] args) {

//        String s = "ifailuhkqq";
        String s = "bca";
        String sort = sort(s);
        System.out.println(sort);

        int i = sherlockAndAnagrams(s);
        System.out.println(i);
    }


    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        HashMap<String, Integer> fre = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int len = 1; len <= chars.length; len++) {
            for (int j = 0; j <= chars.length - len; j++) {
                // 所有子串
                String substring = s.substring(j, j + len);
                String sortedStr = sort(substring);
                if (fre.keySet().contains(sortedStr)) {
                    Integer count = fre.get(sortedStr);
                    fre.put(sortedStr, ++count);
                } else {
                    fre.put(sortedStr, 1);
                }
            }
        }
        Integer result = fre.values().stream().reduce(0, (a, b) -> a += b * (b - 1) / 2);
        return result;
    }

    static String sort(String substring) {
        char[] chars = substring.toCharArray();
        Arrays.sort(chars);
        String value = String.valueOf(chars);
        return value;
    }

}
