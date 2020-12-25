package com.enjoy.hackerrank.string;

import java.util.*;

/**
 * @author Michael
 * @create 12/21/2020 2:47 PM
 */
public class SherlockValid {

    public static void main(String[] args) {
//        String s = "abbac";
//        String s = "aabbccddeefghi";
//        String s = "aabbcd";
        String s = "aabbc";


        String valid = isValid(s);
        System.out.println(valid);
    }


    // 减1后，保证其它都一样即代码有效。
    // Complete the isValid function below.
    static String isValid(String s) {

        char[] chars = s.toCharArray();
        int[] fre = new int[26];
        if (chars.length == 1) return "YES";

        for (int i = 0; i < chars.length; i++) {
            fre[chars[i] - 97]++;
        }

        System.out.println(Arrays.toString(fre));

        for (int i = 0; i < fre.length; i++) {
            fre[i]--;
            HashSet set = new HashSet();
            for (int j = 0; j < fre.length; j++) {
                if (fre[j]>0){
                    set.add(fre[j]);
                }
            }
            if (set.size()==1)
                return "YES";
            fre[i]++;
        }
        return "NO";
    }
}
